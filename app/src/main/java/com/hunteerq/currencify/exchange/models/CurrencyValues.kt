package com.hunteerq.currencify.exchange.models

import java.util.*

data class CurrencyValues(val rates : Map<String, Double>, val base : String, val date : Date )