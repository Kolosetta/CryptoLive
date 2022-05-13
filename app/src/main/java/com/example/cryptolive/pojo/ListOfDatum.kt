package com.example.cryptolive.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListOfDatum(
    @SerializedName("Data")
    @Expose
    val dataList: List<Datum>? = null
)
