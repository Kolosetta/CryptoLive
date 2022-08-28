package com.example.cryptolive.domain

import com.example.cryptolive.domain.repository.CoinRepository
import javax.inject.Inject

class LoadDataInBackGroundUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke() = repository.loadDataInBackGround()
}