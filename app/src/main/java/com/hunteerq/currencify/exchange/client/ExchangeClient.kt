package com.hunteerq.currencify.exchange.client

import java.util.*

class ExchangeClient {

    private val URI: String = "https://api.exchangeratesapi.io/latest"

    val updateCurrencies = object : TimerTask() {
        override fun run() = TODO("API CALLS")
    }

}
