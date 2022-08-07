package com.example.cryptolive.domain

import com.example.cryptolive.domain.repository.CoinRepository

class GetCoinInfoListUseCase(private val repository: CoinRepository) {
    operator fun invoke() = repository.getCoinInfoList()
}