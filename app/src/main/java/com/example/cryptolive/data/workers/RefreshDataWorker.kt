package com.example.cryptolive.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.cryptolive.data.database.AppDatabase
import com.example.cryptolive.data.mapper.CoinMapper
import com.example.cryptolive.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    private val coinInfoDao = AppDatabase.getInstance(context).CoinInfoDao()
    private val apiService = ApiFactory.apiService
    private val mapper = CoinMapper()

    override suspend fun doWork(): Result {
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

    companion object{
        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}