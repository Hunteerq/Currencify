package com.hunteerq.currencify.exchange

import com.hunteerq.currencify.exchange.models.CurrencyValues
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface CurrencyService {

    @GET("/latest")
    fun getCurrency(
        @Query("base") currency : String
    ): Call<CurrencyValues>

}