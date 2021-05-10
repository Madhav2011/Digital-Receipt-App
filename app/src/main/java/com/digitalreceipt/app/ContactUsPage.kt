package com.digitalreceipt.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactUsPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us_page)

        var homebutton: AppCompatButton = findViewById(R.id.homebtn)
       /* var categorybutton: AppCompatButton = findViewById(R.id.categorybtn)*/
        var settingbutton: AppCompatButton = findViewById(R.id.settingbtn)
        var floatingaddicon: FloatingActionButton = findViewById(R.id.addiconbottom)


        floatingaddicon.setOnClickListener {
            var intent: Intent = Intent(applicationContext, ProductForm::class.java)
            startActivity(intent)
        }

        homebutton.setOnClickListener {
            var intent: Intent = Intent(applicationContext, homepage1::class.java)
            startActivity(intent)
        }

        /*categorybutton.setOnClickListener {
            var intent: Intent = Intent(applicationContext, Categorypage::class.java)
            startActivityForResult(intent, 202)
        }*/
        settingbutton.setOnClickListener {
            var intent: Intent = Intent(applicationContext, SettingPage::class.java)
            startActivity(intent)
        }
    }
}