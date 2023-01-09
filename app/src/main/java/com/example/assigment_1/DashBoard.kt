package com.example.assigment_1

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
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
    lateinit var drawerLayout: DrawerLayout
   // lateinit var  cursor  : Cursor
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        var helper = Helper(this,null)
        db = helper.readableDatabase
        val navigation:NavigationView = findViewById(R.id.dah_nav_draw)
         drawerLayout= findViewById(R.id.dash_drawaer)

        val SharedPreferences : SharedPreferences = getSharedPreferences("MyPreff", MODE_PRIVATE)
        var mymail = SharedPreferences.getString("mail","").toString()
        //var inflate : View = layoutInflater.inflate(R.layout.draw_nav,null)
//        var inflater = LayoutInflater.from(applicationContext).inflate(R.layout.draw_nav,null)
        //var inflate : View =  layoutInflater.inflate(R.layout.draw_nav,null,false)
//        var text1 :TextView = inflater.findViewById(R.id.drw_nav_email)
//        text1.text = mymail
        var navhederview = navigation.inflateHeaderView(R.layout.draw_nav)
        var tvheder = navhederview.findViewById<TextView>(R.id.drw_nav_email)
        var tvheader1 = navhederview.findViewById<TextView>(R.id.drw_nav_name)
        tvheder.text = mymail
        var args = listOf<String>(mymail).toTypedArray()

//        var rs = db.rawQuery("SELECT Name FROM Table_Client WHERE Email == ? ",args)
//        var rs1 =  db.execSQL("SELECT Name FROM Table_Client WHERE Email == ? ",args)
//        //var n = rs.toString()
//        tvheader1.text = rs1.toString()
       //tvheader1.append(helper.getname(mymail).toString())
            //tvheader1.text = helper.getname(email = mymail).toString()
            //dash_tv.text = mymail
             //var a = dash_tv.text.toString()
            //val rs = db.execSQL("SELECT Name FROM Table_Client WHERE Email == $mymail")
            // val rs =  db.rawQuery("SELECT Name FROM Table_Client WHERE Email == $mymail",null)
           //dash_tv.text = helper.getname(mymail).toString()

        //this is for bottom navigation bar
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
        //this is menu drawer fragment
        toogale = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toogale)
        toogale.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigation.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.side_menu_payment-> changeFragment1(PaymentFragment())
                R.id.side_menu_address-> changeFragment1(AddressesFragment())
                R.id.side_menu_password-> changeFragment1(PasswordFragment())
                R.id.side_menu_household-> changeFragment1(HouseHoldFragment())
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

    private fun changeFragment(fragment : Fragment)
    {
        val fragmentmanger =  supportFragmentManager
        val fragmenttransaction =  fragmentmanger.beginTransaction()
        fragmenttransaction.replace(R.id.dash_frame,fragment)
        fragmenttransaction.commit()


    }
    //drawer navigation
    private fun changeFragment1(fragment : Fragment)
    {
        val fragmentmanger =  supportFragmentManager
        val fragmenttransaction =  fragmentmanger.beginTransaction()
        fragmenttransaction.replace(R.id.dash_frame,fragment)
        fragmenttransaction.commit()
        drawerLayout.closeDrawers()


    }
}