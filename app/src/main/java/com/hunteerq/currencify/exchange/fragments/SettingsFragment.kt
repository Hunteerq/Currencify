package com.hunteerq.currencify.exchange.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.hunteerq.currencify.R
import com.hunteerq.currencify.exchange.client.CurrencyTypes

class SettingsFragment : Fragment() {

    private lateinit var rootView : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        val currenciesSpinner : Spinner = rootView.findViewById(R.id.settingsOptionSpinner)
        currenciesSpinner.adapter = getCurrenciesAdapter()
        currenciesSpinner.onItemSelectedListener = getOnSelectListener()
        return rootView
    }

    private fun getCurrenciesAdapter(): ArrayAdapter<String?> {
        val currencies = CurrencyTypes.values().map { curr -> curr.toString() }
        return ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, currencies)
    }


    private fun getOnSelectListener(): AdapterView.OnItemSelectedListener? {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
                with(sharedPref.edit()) {
                    putString(CurrencyFragment.CURRENCY_KEY, parent?.getItemAtPosition(position).toString())
                    apply()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("IDK YET")
            }
        }
    }

}
