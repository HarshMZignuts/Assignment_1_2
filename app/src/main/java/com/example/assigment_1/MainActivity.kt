package com.example.assigment_1

import android.content.Intent
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
        img.startAnimation(anim)

        Handler().postDelayed({
                              var intent =  Intent(applicationContext,LoginScreen::class.java)
            startActivity(intent)
            finish()
        },5000)


    }
}