package com.digitalreceipt.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    lateinit var entUname:EditText
    lateinit var entUpass:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonlogin: AppCompatButton = findViewById(R.id.btnlogin)
        var buttonregister: AppCompatButton = findViewById(R.id.btnregister)

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
        if (entUname.equals("admin") && entUpass.equals("admin"))
        {
            var intent: Intent = Intent(applicationContext, homepage1::class.java)
            startActivity(intent)
        }
    }
}