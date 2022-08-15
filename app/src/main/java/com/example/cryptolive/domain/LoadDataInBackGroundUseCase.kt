package com.example.cryptolive.domain

import com.example.cryptolive.domain.repository.CoinRepository

class LoadDataInBackGroundUseCase(private val repository: CoinRepository) {
    operator fun invoke() = repository.loadDataInBackGround()
}