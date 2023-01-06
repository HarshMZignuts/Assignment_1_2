package com.example.assigment_1

import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
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
            //dash_tv.text = mymail
             //var a = dash_tv.text.toString()
            //val rs = db.execSQL("SELECT Name FROM Table_Client WHERE Email == $mymail")
            // val rs =  db.rawQuery("SELECT Name FROM Table_Client WHERE Email == $mymail",null)
           //dash_tv.text = helper.getname(mymail).toString()
        changeFragment(HomeFragment())
        bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId)
            {
                R.id.menu_home -> changeFragment(HomeFragment())
                R.id.menu_vendors -> changeFragment(VendorFragment())
                R.id.menu_category -> changeFragment(CategoryFragment())
                R.id.menu_list -> changeFragment(ListFragment())
                R.id.menu_more -> changeFragment(MoreFragment())
                else->{


                }
            }
            true

        }



    }

    private fun changeFragment(fragment : Fragment)
    {
        val fragmentmanger =  supportFragmentManager
        val fragmenttransaction =  fragmentmanger.beginTransaction()
        fragmenttransaction.replace(R.id.dash_frame,fragment)
        fragmenttransaction.commit()


    }
}