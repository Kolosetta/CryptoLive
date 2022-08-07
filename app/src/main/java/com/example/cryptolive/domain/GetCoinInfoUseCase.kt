package com.example.cryptolive.domain

import com.example.cryptolive.domain.repository.CoinRepository

class GetCoinInfoUseCase(private val repository: CoinRepository) {
    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}