package com.example.cryptolive.domain

data class CoinInfo(
  val price: String?,
  val dayLowPrice: String?,
  val dayHighPrice: String?,
  val lastMarket: String?,
  val lastUpdate: Long?,
  val fromSymbol: String,
  val toSymbol: String?,
  val imgUrl: String?
)