package com.example.assigment_1

import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.activity_login_screen.*

class DashBoard : AppCompatActivity() {
    lateinit var db : SQLiteDatabase
    lateinit var toogale : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        var helper = Helper(applicationContext)
        db = helper.readableDatabase
        val navigation:NavigationView = findViewById(R.id.dah_nav_draw)
        val drawerLayout: DrawerLayout = findViewById(R.id.dash_drawaer)

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
        toogale = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toogale)
        toogale.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigation.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.side_menu_payment-> changeFragment(PaymentFragment())
                R.id.side_menu_address-> changeFragment(AddressesFragment())
                R.id.side_menu_password-> changeFragment(PasswordFragment())
                R.id.side_menu_household-> changeFragment(HomeFragment())
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

    private fun changeFragment(fragment : Fragment)
    {
        val fragmentmanger =  supportFragmentManager
        val fragmenttransaction =  fragmentmanger.beginTransaction()
        fragmenttransaction.replace(R.id.dash_frame,fragment)
        fragmenttransaction.commit()


    }
}