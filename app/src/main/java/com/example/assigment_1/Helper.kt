package com.example.assigment_1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Helper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object
    {
        private const val DATABASE_VERSION = 1
        private const val  DATABASE_NAME = "ClientDataBase.db"
        private const val TBL_CLIENT = "Table_Client"
        private const val ID = "_id"
        private const val NAME = "Name"
        private const val EMAIL = "Email"
        private const val PASSWORD = "Password"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TBL_CLIENT ($ID integer primary key autoincrement,$NAME text,$EMAIL text,$PASSWORD text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertClient(clt : ClientData) : Long{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(ID,clt.id)
        cv.put(NAME,clt.namecl)
        cv.put(EMAIL,clt.email)
        cv.put(PASSWORD,clt.password)
        val success =  db.insert(TBL_CLIENT,null,cv)
        db.close()
        return success

    }


}