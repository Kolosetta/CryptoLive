package com.example.cryptolive.domain.repository

import androidx.lifecycle.LiveData
import com.example.cryptolive.domain.CoinInfo

interface CoinRepository {
    fun getCoinInfoList(): LiveData<List<CoinInfo>>
    fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo>
}