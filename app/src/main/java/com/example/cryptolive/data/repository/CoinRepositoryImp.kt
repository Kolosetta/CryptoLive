package com.example.cryptolive.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.cryptolive.data.database.AppDatabase
import com.example.cryptolive.data.mapper.CoinMapper
import com.example.cryptolive.data.network.ApiFactory
import com.example.cryptolive.domain.CoinInfo
import com.example.cryptolive.domain.repository.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImp(private val application: Application) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).CoinInfoDao()
    private val apiService = ApiFactory.apiService
    private val mapper = CoinMapper()

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

    //Загружает информацию о валютах и кладет в бд
    override suspend fun loadData() {
        while(true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fromSymbols = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fromSymbols)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val coinInfoDbModelList = coinInfoDtoList.map {
                    mapper.mapDtoToDbModel(it)
                }
                coinInfoDao.insertPriceList(coinInfoDbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
}