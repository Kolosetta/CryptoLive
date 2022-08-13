package com.example.cryptolive.domain

data class CoinInfo(
  val price: String?,
  val dayLowPrice: String?,
  val dayHighPrice: String?,
  val lastMarket: String?,
  val lastUpdate: String,
  val fromSymbol: String,
  val toSymbol: String?,
  val change24Hour: String?,
  val imgUrl: String
)