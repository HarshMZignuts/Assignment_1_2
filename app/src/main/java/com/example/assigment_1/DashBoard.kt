package com.example.assigment_1

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.activity_login_screen.*

class DashBoard : AppCompatActivity() {
    lateinit var db : SQLiteDatabase
    lateinit var toogale : ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    @SuppressLint("Range", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        var helper = Helper(this,null)
        db = helper.readableDatabase
        val navigation:NavigationView = findViewById(R.id.dah_nav_draw)
         drawerLayout= findViewById(R.id.dash_drawaer)

        val SharedPreferences : SharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE)
        var mymail = SharedPreferences.getString("mail","").toString()

        var navhederview = navigation.inflateHeaderView(R.layout.draw_nav)
        var tvheder = navhederview.findViewById<TextView>(R.id.drw_nav_email)
        var tvheader1 = navhederview.findViewById<TextView>(R.id.drw_nav_name)
        tvheder.text = mymail
        var args = listOf<String>(mymail).toTypedArray()
        // this is for show name in side drawer header
        var rs = db.rawQuery("SELECT Name FROM Table_Client WHERE Email = ? LIMIT 1",args)
       rs.moveToNext()
        //Log.e("@Tag", rs.getString(rs.getColumnIndex("Name")))
      tvheader1.text = rs.getString(rs.getColumnIndex("Name"))


        //this is for bottom navigation bar
        changeFragmentNavigationBar(HomeFragment())
        bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId)
            {
                R.id.menu_home -> changeFragmentNavigationBar(HomeFragment())
                R.id.menu_vendors -> changeFragmentNavigationBar(VendorFragment())
                R.id.menu_category -> changeFragmentNavigationBar(CategoryFragment())
                R.id.menu_list -> changeFragmentNavigationBar(ListFragment())
                R.id.menu_more -> changeFragmentNavigationBar(MoreFragment())
                else->{


                }
            }
            true

        }
        //this is menu drawer fragment
        toogale = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toogale)
        toogale.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigation.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.side_menu_payment-> changeFragmentDrawer(PaymentFragment())
                R.id.side_menu_address-> changeFragmentDrawer(AddressesFragment())
                R.id.side_menu_password-> changeFragmentDrawer(PasswordFragment())
                R.id.side_menu_household-> changeFragmentDrawer(HouseHoldFragment())
                R.id.side_menu_Logout->
                {
                    // Logout and clear SharedPreference data
                    var shareditor = SharedPreferences.edit()
                    shareditor.clear()
                    shareditor.commit()
                    var intent = Intent(applicationContext,LoginScreen::class.java)
                    startActivity(intent)
                    finish()

                }
            }
            true
        }


    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toogale.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    //bottom navigation bar

    private fun changeFragmentNavigationBar(fragment : Fragment)
    {
        val fragmentmanger =  supportFragmentManager
        val fragmenttransaction =  fragmentmanger.beginTransaction()
        fragmenttransaction.replace(R.id.dash_frame,fragment)
        fragmenttransaction.commit()


    }
    //drawer navigation
    private fun changeFragmentDrawer(fragment : Fragment)
    {
        val fragmentmanger =  supportFragmentManager
        val fragmenttransaction =  fragmentmanger.beginTransaction()
        fragmenttransaction.replace(R.id.dash_frame,fragment)
        fragmenttransaction.commit()
        drawerLayout.closeDrawers()


    }
}