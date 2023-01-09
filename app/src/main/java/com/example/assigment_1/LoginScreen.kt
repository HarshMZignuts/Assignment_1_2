package com.example.assigment_1

import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreen : AppCompatActivity() {
    lateinit var db : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        var helper = Helper(this,null)
        db = helper.readableDatabase
        btn_new_user.setOnClickListener {

            var intent = Intent(applicationContext,NewUser1::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {

            login()
        }



    }
    private fun login(){
        var p = Patterns.EMAIL_ADDRESS
        if(TextUtils.isEmpty(et_email.text.toString()))
        {
            et_email.setError("Enter Email First")
            et_email.requestFocus()
        }
//        else if (et_email.text.toString().trim() != p.toString())
//        {
//            et_email.setError("Enter Email Properly")
//            et_email.requestFocus()
//        }
        else if(TextUtils.isEmpty(et_pass.text.toString()))
        {
            et_pass.setError("Enter Password First")
            et_pass.requestFocus()
        }
        else
        {
            var args = listOf<String>(et_email.text.toString(),et_pass.text.toString()).toTypedArray()
            var rs = db.rawQuery("SELECT * FROM Table_Client WHERE Email = ? AND Password = ?",args)
            if(rs.moveToNext())
            {
                val SharedPreferences : SharedPreferences = this.getSharedPreferences("MyPreff", MODE_PRIVATE)
                var myeditor : SharedPreferences.Editor = SharedPreferences.edit()
                myeditor.putString("mail",et_email.text.toString())
                myeditor.apply()
                myeditor.commit()
                var intent =  Intent(applicationContext,DashBoard::class.java)
                startActivity(intent)
                finish()

            }
            else
            {
                Toast.makeText(applicationContext,"Invalid Credential",Toast.LENGTH_LONG).show()

            }
        }
    }
}