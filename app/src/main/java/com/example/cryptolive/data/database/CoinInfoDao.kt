package com.example.cryptolive.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptolive.data.database.model.CoinInfoDbModel

@Dao
interface CoinInfoDao {
    @Query("SELECT * FROM full_price ORDER BY lastUpdate DESC")
    fun getPriceList(): LiveData<List<CoinInfoDbModel>>

    @Query("SELECT * FROM full_price WHERE fromSymbol == :fSym LIMIT 1")
    fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinInfoDbModel>)
}