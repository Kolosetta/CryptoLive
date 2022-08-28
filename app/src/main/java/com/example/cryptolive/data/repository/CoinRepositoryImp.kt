package com.example.cryptolive.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptolive.data.database.CoinInfoDao
import com.example.cryptolive.data.mapper.CoinMapper
import com.example.cryptolive.data.network.ApiService
import com.example.cryptolive.data.workers.RefreshDataWorker
import com.example.cryptolive.domain.CoinInfo
import com.example.cryptolive.domain.repository.CoinRepository
import javax.inject.Inject


class CoinRepositoryImp @Inject constructor(
    private val application: Application,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
) : CoinRepository {

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> =
        Transformations.map(coinInfoDao.getPriceList()) { list ->
            list?.map {
                mapper.mapDbModelToEntity(it)
            }
        }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> =
        Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) { coinInfo ->
            coinInfo?.let {
                mapper.mapDbModelToEntity(it)
            }
        }

    //Запускает Worker, который в фоне загружает данные о валютах
    override fun loadDataInBackGround() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }

    override suspend fun loadDataManually() {
        Log.i("Lol", "Пошла загрузка")
        val topCoins = apiService.getTopCoinsInfo(limit = 50)
        val fromSymbols = mapper.mapNamesListToString(topCoins)
        val jsonContainer = apiService.getFullPriceList(fSyms = fromSymbols)
        val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
        val coinInfoDbModelList = coinInfoDtoList.map {
            mapper.mapDtoToDbModel(it)
        }
        coinInfoDao.insertPriceList(coinInfoDbModelList)
        Log.i("Lol", "Остановилась загрузка")
    }

}