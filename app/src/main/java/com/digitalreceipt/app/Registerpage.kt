package com.digitalreceipt.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registerpage.*

class Registerpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registerpage)


        clickheretext.setOnClickListener(View.OnClickListener() {
            var intent: Intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        })
        }
    }
