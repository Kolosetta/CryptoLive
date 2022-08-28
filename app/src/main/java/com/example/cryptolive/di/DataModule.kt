package com.example.cryptolive.di

import android.app.Application
import com.example.cryptolive.data.database.AppDatabase
import com.example.cryptolive.data.database.CoinInfoDao
import com.example.cryptolive.data.network.ApiFactory
import com.example.cryptolive.data.network.ApiService
import com.example.cryptolive.data.repository.CoinRepositoryImp
import com.example.cryptolive.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @Binds
    fun bindCoinRepository(repositoryImp: CoinRepositoryImp): CoinRepository

    companion object {
        @Provides
        fun provideCoinInfoDao(application: Application): CoinInfoDao {
            return AppDatabase.getInstance(application).CoinInfoDao()
        }

        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}