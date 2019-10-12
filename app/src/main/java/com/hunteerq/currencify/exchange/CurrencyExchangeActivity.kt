package com.hunteerq.currencify.exchange

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.hunteerq.currencify.MainActivity
import com.hunteerq.currencify.R
import com.hunteerq.currencify.exchange.fragments.CurrencyFragment
import com.hunteerq.currencify.exchange.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_currency_exchange.*

class CurrencyExchangeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment : Fragment? = null
        when (item.itemId) {
            R.id.navigation_settings -> {
                selectedFragment =  SettingsFragment()
            }
            R.id.navigation_currencies -> {
                selectedFragment = CurrencyFragment()
            }
            R.id.navigation_logout -> {
                val mainActivity = Intent(this, MainActivity::class.java)
                startActivity(mainActivity)
            }
        }
        if (selectedFragment != null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container, selectedFragment).commit()
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_exchange)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
            CurrencyFragment()).commit()
    }
}
