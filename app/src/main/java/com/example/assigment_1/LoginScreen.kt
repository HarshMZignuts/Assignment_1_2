package com.example.assigment_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class LoginScreen : AppCompatActivity() {
    lateinit var new_user : MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        new_user = findViewById(R.id.btn_new_user)
        new_user.setOnClickListener {

            var intent = Intent(applicationContext,NewUser::class.java)
            startActivity(intent)
        }
    }
}