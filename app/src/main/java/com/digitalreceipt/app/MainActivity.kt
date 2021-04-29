package com.digitalreceipt.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    lateinit var entUname:TextInputLayout
    lateinit var entUpass:TextInputLayout
    lateinit var btnLogin:AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*var buttonlogin: AppCompatButton = findViewById(R.id.btlogin)*/
        var buttonregister: AppCompatButton = findViewById(R.id.btregister)

        buttonregister.setOnClickListener {
            var intent: Intent = Intent(applicationContext, Registerpage::class.java)
            startActivity(intent)
        }
        /*buttonlogin.setOnClickListener {
            var intent: Intent = Intent(applicationContext, homepage1::class.java)
            startActivity(intent)
        }*/
        entUname=findViewById(R.id.entusername)
        entUpass=findViewById(R.id.entpassword)
        btnLogin=findViewById(R.id.btlogin)
        btnLogin.setOnClickListener {
            var uname=entUname.editText!!.text.toString()
            var pass= entUpass.editText!!.text.toString()
            if(uname.equals("admin") && pass.equals("admin"))
            {
                Toast.makeText(applicationContext,"Login Successful!!",Toast.LENGTH_LONG).show()
                    var intent: Intent = Intent(applicationContext, homepage1::class.java)
                    startActivity(intent)
            }
            else
            {
                Toast.makeText(applicationContext,"Invalid Credential!!",Toast.LENGTH_LONG).show()
            }
        }
    }
}