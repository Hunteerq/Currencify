package com.hunteerq.currencify.start.services

import android.content.Context
import android.database.Cursor
import com.hunteerq.currencify.db.models.UserLogin
import com.hunteerq.currencify.db.sqlite.UserManagerRepository

class LoginService(context : Context) {

    private var userRepository : UserManagerRepository = UserManagerRepository(context)


    fun userAuthorization(userLogin : UserLogin) : Boolean {
        val usersCursor : Cursor? = userRepository.getAllUsers(userLogin)
        usersCursor.

    }
}
