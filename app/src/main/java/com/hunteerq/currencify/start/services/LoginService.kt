package com.hunteerq.currencify.start.services

import android.content.Context
import com.hunteerq.currencify.db.models.UserLogin
import com.hunteerq.currencify.db.sqlite.UserManagerRepository
import com.hunteerq.currencify.start.services.mappers.UserMapper

class LoginService(context : Context) {

    private val userMapper  = UserMapper()

    private val userRepository = UserManagerRepository(context)


    fun userAuthorization(userLogin : UserLogin) : Boolean {
        return userMapper.mapCursorToUserAmount(userRepository.userExists(userLogin)) > 0
    }
}
