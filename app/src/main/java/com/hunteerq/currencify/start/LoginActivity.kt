package com.hunteerq.currencify.start

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hunteerq.currencify.MainActivity
import com.hunteerq.currencify.R
import com.hunteerq.currencify.db.models.UserLogin
import com.hunteerq.currencify.exchange.CurrencyExchangeActivity
import com.hunteerq.currencify.start.services.LoginService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val loginService : LoginService = LoginService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onLogin(view : View) {
        val user = UserLogin(loginUsernameInput.text.toString(),
            loginPasswordInput.text.toString())
        var nextView : Intent? = null

        if (loginService.userAuthorization(user)) {
            nextView = Intent(this, CurrencyExchangeActivity::class.java)
        } else {
          nextView = Intent(this, MainActivity::class.java)
        }
        startActivity(nextView)

    }
}
