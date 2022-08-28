package com.example.cryptolive.di

import androidx.lifecycle.ViewModel
import com.example.cryptolive.presentation.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @VIewModelKey(CoinViewModel::class)
    fun bindCoinViewModule(viewModel: CoinViewModel) : ViewModel
}