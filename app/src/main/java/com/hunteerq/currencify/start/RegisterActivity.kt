package com.hunteerq.currencify.start

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hunteerq.currencify.MainActivity
import com.hunteerq.currencify.R
import com.hunteerq.currencify.db.models.User
import com.hunteerq.currencify.db.sqlite.UserManagerRepository
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {


    private var userRepository : UserManagerRepository = UserManagerRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun onClickCreateAccount(view: View) {
        val user = User(null, usernameInput.text.toString(),
            passwordInput.text.toString(), mailInput.text.toString())
        userRepository.createUser(user)
        val moveToMain = Intent(this, MainActivity::class.java)
        startActivity(moveToMain)
    }

}
