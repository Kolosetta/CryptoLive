package com.example.cryptolive.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price_list")
data class CoinInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val price: String?,
    val dayLowPrice: String?,
    val dayHighPrice: String?,
    val lastMarket: String?,
    val lastUpdate: Long?,
    val toSymbol: String?,
    val change24Hour: String?,
    val imgUrl: String
)
