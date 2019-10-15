package com.hunteerq.currencify.exchange.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hunteerq.currencify.R
import com.hunteerq.currencify.exchange.client.CurrencyTypes
import com.hunteerq.currencify.exchange.client.ExchangeClient
import kotlinx.android.synthetic.main.fragment_currency.view.*

class CurrencyFragment : Fragment() {

    private lateinit var exchangeClient : ExchangeClient

    private lateinit var currencyAsyncHandler: Handler

    private lateinit var rootView : View


    companion object {
        const val CURRENCY_KEY : String = "CURRENCY"
        const val UPDATE_TIME_IN_MS : Long = 10000
        val DEFAULT_CURRENCY : String =  CurrencyTypes.PLN.toString()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_currency, container, false)
        setCurrencyTitleFromSettings()
        currencyAsyncHandler = Handler(Looper.getMainLooper())
        return rootView
    }

    private fun setCurrencyTitleFromSettings() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        exchangeClient = ExchangeClient(sharedPref, rootView, context)
        val selectedCurrency = sharedPref.getString(CURRENCY_KEY, DEFAULT_CURRENCY) ?:
        CurrencyTypes.PLN.toString()
        rootView.currencyTitle.text = selectedCurrency
    }

    override fun onPause() {
        super.onPause()
        currencyAsyncHandler.removeCallbacks(updateCurrencies)
    }

    override fun onResume() {
        super.onResume()
        currencyAsyncHandler.post(updateCurrencies)
    }


    private val updateCurrencies = object : Runnable {
        override fun run() {
            exchangeClient.updateCurrencies()
            currencyAsyncHandler.postDelayed(this, UPDATE_TIME_IN_MS)
        }
    }


}
