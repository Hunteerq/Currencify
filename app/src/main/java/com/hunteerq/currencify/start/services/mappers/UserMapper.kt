package com.hunteerq.currencify.start.services.mappers

import android.database.Cursor

class UserMapper {

    fun mapSequenceToUser(it: Cursor) {
        val user : String = it.getString(1)
        val password : String = it.getString(2)
        println("$user  $password")
    }

    fun mapCursorToUserAmount(userExists: Cursor?): Int {
        userExists?.moveToFirst()
        return userExists?.getInt(0) ?: 0
    }

}
