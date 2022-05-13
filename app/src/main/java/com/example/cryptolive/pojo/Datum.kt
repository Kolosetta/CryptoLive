package com.example.cryptolive.pojo

import CoinInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum(
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinInfo? = null
)
