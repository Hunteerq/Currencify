package com.hunteerq.currencify.exchange.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hunteerq.currencify.R
import com.hunteerq.currencify.exchange.client.CurrencyTypes
import com.hunteerq.currencify.exchange.client.ExchangeClient
import kotlinx.android.synthetic.main.fragment_currency.view.*

class CurrencyFragment : Fragment() {

    private val exchangeClient = ExchangeClient()

    private lateinit var rootView : View

    private var selectedCurrency : String = CurrencyTypes.PLN.toString()

    companion object {
        const val CURRENCY_KEY : String = "CURRENCY"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_currency, container, false)
        setCurrencyTitleFromSettings()
        return rootView
    }

    private fun setCurrencyTitleFromSettings() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        selectedCurrency = sharedPref.getString(CURRENCY_KEY, CurrencyTypes.PLN.toString()) ?:
                CurrencyTypes.PLN.toString()
        rootView.currencyTitle.text = selectedCurrency
    }
}
