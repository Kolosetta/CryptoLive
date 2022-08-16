package com.example.cryptolive.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptolive.data.repository.CoinRepositoryImp
import com.example.cryptolive.domain.*

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    //Пока без DI нарушается принцип Clean Architecture
    private val repository = CoinRepositoryImp(application)

    private val getCoinListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataInBackGroundUseCase = LoadDataInBackGroundUseCase(repository)
    private val loadDataManuallyUseCase = LoadDataManuallyUseCase(repository)


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