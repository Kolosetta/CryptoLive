package com.example.cryptolive.pojo

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPriceInfoRawData(
    @SerializedName("RAW")
    @Expose
    val coinPriceInfoJsonObject: JsonObject? = null //json объект, в который приходит список криптовалют, которые в себе содержат объекты обычных валют (coinPrices)
)
