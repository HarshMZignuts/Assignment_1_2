package com.example.assigment_1

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceActivity.Header
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val img =  findViewById<ImageView>(R.id.img_view)
        val anim =  AnimationUtils.loadAnimation(this,R.anim.anim_1)
        val shr : SharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        val mymail : String = shr.getString("mail", "").toString()


        img.startAnimation(anim)

        Handler().postDelayed({
            // this is for one time login using sharedPreference
            if(mymail != null && mymail != "")
            {

                var intent  =  Intent(applicationContext,DashBoard::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
            var intent =  Intent(applicationContext,LoginScreen::class.java)
            startActivity(intent)
            finish()
            }
        },5000)


    }
}