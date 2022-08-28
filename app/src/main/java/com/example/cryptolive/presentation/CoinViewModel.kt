package com.example.cryptolive.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cryptolive.domain.*
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataInBackGroundUseCase: LoadDataInBackGroundUseCase,
    private val loadDataManuallyUseCase: LoadDataManuallyUseCase
) : ViewModel() {


    val coinInfoList = getCoinListUseCase()

    fun getDetailInfo(fromSymbols: String): LiveData<CoinInfo> {
        return getCoinInfoUseCase(fromSymbols)
    }

    fun loadDataInBackground(){
        loadDataInBackGroundUseCase()
    }

    suspend fun loadDataManually(){
        loadDataManuallyUseCase.invoke()
    }
}