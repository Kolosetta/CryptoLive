package com.example.cryptolive.data.mapper

import com.example.cryptolive.data.database.model.CoinInfoDbModel
import com.example.cryptolive.data.network.model.CoinInfoDto
import com.example.cryptolive.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptolive.data.network.model.CoinNamesListDto
import com.example.cryptolive.domain.CoinInfo
import com.google.gson.Gson

class CoinMapper {
    fun mapDtoToDbModel(coinInfo: CoinInfoDto): CoinInfoDbModel = CoinInfoDbModel(
        fromSymbol = coinInfo.fromSymbol,
        price = coinInfo.price,
        dayLowPrice = coinInfo.lowDay,
        dayHighPrice = coinInfo.highDay,
        lastMarket = coinInfo.lastMarket,
        lastUpdate = coinInfo.lastUpdate,
        toSymbol = coinInfo.toSymbol,
        imgUrl = coinInfo.imageUrl
    )

    //Преобразует Json объект в лист CoinInfo
    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    //Преобразует список имен криптовалют, в строку с разделением их в виде запятой
    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map {
            it.coinName?.name
        }?.joinToString(",") ?: ""
    }

    //Преобразует coinInfo из БД в coinInfo бизнес слоя
    fun mapDbModelToEntity(coinInfoDbModel: CoinInfoDbModel): CoinInfo = CoinInfo(
        fromSymbol = coinInfoDbModel.fromSymbol,
        price = coinInfoDbModel.price,
        dayLowPrice = coinInfoDbModel.dayLowPrice,
        dayHighPrice = coinInfoDbModel.dayHighPrice,
        lastMarket = coinInfoDbModel.lastMarket,
        lastUpdate = coinInfoDbModel.lastUpdate,
        toSymbol = coinInfoDbModel.toSymbol,
        imgUrl = coinInfoDbModel.imgUrl
    )

}