package com.example.cryptolive.presentation

import android.app.Application
import com.example.cryptolive.di.DaggerApplicationComponent

class CoinApp : Application() {
    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}