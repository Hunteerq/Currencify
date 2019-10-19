package com.hunteerq.currencify.exchange.client

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.hunteerq.currencify.R
import com.hunteerq.currencify.exchange.CurrencyService
import com.hunteerq.currencify.exchange.fragments.CurrencyFragment
import com.hunteerq.currencify.exchange.models.CurrencyValues
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ExchangeClient (
    private val sharedPreferences: SharedPreferences,
    private val rootView: View,
    private val context: Context?
) {

    private val onlineExchangeUri: String = "https://api.exchangeratesapi.io/"

    private val httpClient = OkHttpClient.Builder()

    private val retrofitClient : Retrofit = Retrofit.Builder()
        .baseUrl(onlineExchangeUri)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    fun updateCurrencies() {
        val currency = sharedPreferences.getString(CurrencyFragment.CURRENCY_KEY, CurrencyFragment.DEFAULT_CURRENCY)
            ?: CurrencyFragment.DEFAULT_CURRENCY

        val currencyService = retrofitClient.create(CurrencyService::class.java)
        val syncCall: Call<CurrencyValues> = currencyService.getCurrency(currency)

        updateViewAndCurrencies(syncCall)
    }

    private fun updateViewAndCurrencies(syncCall: Call<CurrencyValues>) {
        syncCall.enqueue(object : Callback<CurrencyValues> {
            override fun onResponse(call: Call<CurrencyValues>, response: Response<CurrencyValues>) {
                val currencyValues = response.body()
                updateView(currencyValues)
            }

            override fun onFailure(call: Call<CurrencyValues>, t: Throwable) {
                Toast.makeText(context, "Could not update", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun updateView(currencyValues: CurrencyValues?) {
        val formattedCurrencies = currencyValues?.rates?.map { entry -> entry.key + ":  "+ entry.value.toString() }
        val currenciesAdapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, formattedCurrencies!!)
        val listView = rootView.findViewById<ListView>(R.id.currenciesListView)
        listView.adapter = currenciesAdapter
    }

}
