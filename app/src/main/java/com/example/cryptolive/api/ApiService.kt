package com.example.cryptolive.api

import com.example.cryptolive.pojo.ListOfDatum
import io.reactivex.rxjava3.core.Single

interface ApiService {

    fun getTopCoinsInfo(): Single<ListOfDatum>;
}