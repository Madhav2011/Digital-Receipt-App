package com.digitalreceipt.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonlogin: AppCompatButton = findViewById(R.id.btnlogin)
        var buttonregister: AppCompatButton = findViewById(R.id.btnregister)

        buttonregister.setOnClickListener {
            var intent: Intent = Intent(applicationContext, Registerpage::class.java)
            startActivity(intent)
        }
    }
}