package com.hunteerq.currencify.db.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DbProvider(context: Context) : SQLiteOpenHelper(context, "currencify.db", null, 4) {

    companion object {
        const val TABLE_NAME = "users"
        const val ID : String = "id"
        const val USERNAME : String = "username"
        const val PASSWORD : String = "password"
        const val MAIL : String = "mail"
    }


    private val createQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( "+
            "$ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$USERNAME VARCHAR, " +
            "$PASSWORD VARCHAR, " +
            "$MAIL VARCHAR);"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}