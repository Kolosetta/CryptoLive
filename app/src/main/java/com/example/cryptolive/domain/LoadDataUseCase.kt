package com.example.cryptolive.domain

import com.example.cryptolive.domain.repository.CoinRepository

class LoadDataUseCase(private val repository: CoinRepository) {
    suspend operator fun invoke() = repository.loadData()
}