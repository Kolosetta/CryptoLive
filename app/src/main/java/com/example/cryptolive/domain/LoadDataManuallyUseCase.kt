package com.example.cryptolive.domain

import com.example.cryptolive.domain.repository.CoinRepository

class LoadDataManuallyUseCase(private val repository: CoinRepository) {
    suspend operator fun invoke() = repository.loadDataManually()
}