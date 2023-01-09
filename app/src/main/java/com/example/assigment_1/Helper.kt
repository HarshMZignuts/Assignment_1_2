package com.example.assigment_1

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

@SuppressLint("ParcelCreator")
class Helper(context: Context, factory: SQLiteDatabase.CursorFactory?) :SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object
    {
        private const val DATABASE_VERSION = 1
        private const val  DATABASE_NAME = "ClientDataBase.db"
        private const val TBL_CLIENT = "Table_Client"
        private const val ID = "_id"
        public var NAME = "Name"
        public const val EMAIL = "Email"
        public const val PASSWORD = "Password"

    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TBL_CLIENT ($ID integer primary key autoincrement,$NAME text,$EMAIL email,$PASSWORD text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }



}