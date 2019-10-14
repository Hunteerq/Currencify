package com.hunteerq.currencify.start.services.mappers

import android.database.Cursor

class UserMapper {

    fun mapCursorToUserAmount(userExists: Cursor?): Int {
        userExists?.moveToFirst()
        return userExists?.getInt(0) ?: 0
    }

}
