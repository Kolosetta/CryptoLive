package com.example.cryptolive.domain

import com.example.cryptolive.domain.repository.CoinRepository
import javax.inject.Inject

class LoadDataManuallyUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke() = repository.loadDataManually()
}