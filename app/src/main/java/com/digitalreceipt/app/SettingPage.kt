package com.digitalreceipt.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class SettingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_page)

        var buttonaboutus: AppCompatButton = findViewById(R.id.aboutusbtn)
        var buttoncontactus: AppCompatButton = findViewById(R.id.contactusbtn)

        buttonaboutus.setOnClickListener {
            var intent:Intent =Intent(applicationContext,AboutUsPage::class.java)
            startActivity(intent)
        }

        buttoncontactus.setOnClickListener {
            var intent: Intent = Intent(applicationContext,ContactUsPage::class.java)
            startActivity(intent)
        }

    }
}