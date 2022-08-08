package com.example.cryptolive.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.cryptolive.data.database.AppDatabase
import com.example.cryptolive.data.mapper.CoinMapper
import com.example.cryptolive.domain.CoinInfo
import com.example.cryptolive.domain.repository.CoinRepository

class CoinRepositoryImp(application: Application) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).CoinInfoDao()
    private val mapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return coinInfoDao.getPriceList()
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        TODO("Not yet implemented")
    }
}