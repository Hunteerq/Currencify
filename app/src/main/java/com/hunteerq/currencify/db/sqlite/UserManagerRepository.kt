package com.hunteerq.currencify.db.sqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.hunteerq.currencify.db.models.User
import com.hunteerq.currencify.db.models.UserLogin

class UserManagerRepository(context: Context) : DbProvider(context) {

    fun createUser(user: User) {
        val sqliteDb : SQLiteDatabase = this.readableDatabase
        sqliteDb.execSQL(getCreateUserQuery(user))
    }

    private fun getCreateUserQuery(user : User) : String {
        return "INSERT INTO $TABLE_NAME ($USERNAME, $PASSWORD, $MAIL) " +
                "VALUES ('${user.username}', '${user.password}', '${user.mail}');"
    }

    fun getAllUsers(userLogin: UserLogin) : Cursor? {
        val sqliteDb : SQLiteDatabase = this.readableDatabase
        return sqliteDb.rawQuery(getSelectAllUsersQuery(), null)
    }

    private fun getSelectAllUsersQuery() : String {
        return "SELECT * FROM $TABLE_NAME;"
    }

    fun userExists(userLogin: UserLogin): Cursor? {
        val sqliteDb : SQLiteDatabase = this.readableDatabase
        return sqliteDb.rawQuery(getIsUserAuthorizedQuery(userLogin), null)
    }

    private fun getIsUserAuthorizedQuery(userLogin: UserLogin) : String {
        return "SELECT COUNT(*) FROM $TABLE_NAME " +
                "WHERE $USERNAME LIKE '${userLogin.username}' AND " +
                "$PASSWORD LIKE '${userLogin.password}';"
    }

}
