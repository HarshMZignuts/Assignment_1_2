package com.example.assigment_1

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import kotlinx.android.synthetic.main.activity_new_user1.*

class NewUser1 : AppCompatActivity() {
    lateinit var db : SQLiteDatabase
    lateinit var rs : Cursor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user1)
        var helper = Helper(this,null)
        db = helper.readableDatabase
        btn_new_sub.setOnClickListener {

            submite()


        }
    }

    private fun submite()
    {
        var p = Patterns.EMAIL_ADDRESS



        if (TextUtils.isEmpty(et_new_Name.text.toString())){
            et_new_Name.error = "Name field is Empty"
            et_new_Name.requestFocus()

        }
        else if (TextUtils.isEmpty(et_new_Email.text.toString()))
        {
            et_new_Email.error = "Email filed is Empty"
            et_new_Email.requestFocus()
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(et_new_Email.text.toString()).matches())
        {
            et_new_Email.error = "Enter Email Properly"
            et_new_Email.requestFocus()
        }

        else if (TextUtils.isEmpty(et_new_pass.text.toString()))
        {
            et_new_pass.error = "Password  field is Empty"
            et_new_pass.requestFocus()
        }

        else if (et_new_pass.length() < 8)
        {
            et_new_pass.error = "Password Must be in 8 or more char"
            et_new_pass.requestFocus()
        }
        else if (TextUtils.isEmpty(et_new_conf_pass.text.toString()))
        {
            et_new_conf_pass.error = "Conform Password  field is Empty"
            et_new_conf_pass.requestFocus()
        }
        else if (et_new_conf_pass.text.toString().trim() != et_new_pass.text.toString().trim())
        {
            et_new_conf_pass.error = "Please provide a same password"
            et_new_conf_pass.requestFocus()
        }
        else{
            var cv= ContentValues()
            cv.put("Name",et_new_Name.text.toString())
            cv.put("Email",et_new_Email.text.toString())
            cv.put("Password",et_new_pass.text.toString())
            db.insert("Table_Client",null,cv)
            rs.requery()
            var intent  = Intent(applicationContext,LoginScreen::class.java)
            startActivity(intent)
            finish()
        }

    }


}