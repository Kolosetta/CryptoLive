package com.example.cryptolive.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cryptolive.data.repository.CoinRepositoryImp
import com.example.cryptolive.domain.CoinInfo
import com.example.cryptolive.domain.GetCoinInfoListUseCase
import com.example.cryptolive.domain.GetCoinInfoUseCase
import com.example.cryptolive.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    //Пока без DI нарушается принцип Clean Architecture
    private val repository = CoinRepositoryImp(application)

    private val getCoinListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinListUseCase()

    fun getDetailInfo(fromSymbols: String): LiveData<CoinInfo> {
        return getCoinInfoUseCase(fromSymbols)
    }

    init {
        loadDataUseCase()
    }

}