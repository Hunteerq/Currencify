package com.hunteerq.currencify.exchange.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class CurrencyValues(
    @SerializedName("rates") val rates : Map<String, Double>,
    @SerializedName("base") val base : String,
    @SerializedName("date") val date : Date
)