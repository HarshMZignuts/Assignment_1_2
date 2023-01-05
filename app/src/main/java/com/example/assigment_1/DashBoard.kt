package com.example.assigment_1

import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.activity_login_screen.*

class DashBoard : AppCompatActivity() {
    lateinit var db : SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        var helper = Helper(applicationContext)
        db = helper.readableDatabase
        val SharedPreferences : SharedPreferences = getSharedPreferences("MyPreff", MODE_PRIVATE)

        var mymail = SharedPreferences.getString("mail","").toString()
    var a = dash_tv.text.toString()
        val rs = db.execSQL("SELECT Name FROM Table_Client WHERE Email == $mymail")

            dash_tv.text = rs.toString()

    }
}