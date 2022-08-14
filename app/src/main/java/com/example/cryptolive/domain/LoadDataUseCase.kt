package com.example.cryptolive.domain

import com.example.cryptolive.domain.repository.CoinRepository

class LoadDataUseCase(private val repository: CoinRepository) {
    operator fun invoke() = repository.loadData()
}