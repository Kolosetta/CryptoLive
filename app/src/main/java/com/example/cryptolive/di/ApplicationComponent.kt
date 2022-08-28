package com.example.cryptolive.di

import android.app.Application
import com.example.cryptolive.presentation.CoinDetailFragment
import com.example.cryptolive.presentation.CoinListFragment
import com.example.cryptolive.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: CoinListFragment)

    fun inject(fragment: CoinDetailFragment)


    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ) : ApplicationComponent
    }

}