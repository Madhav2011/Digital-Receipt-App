package com.digitalreceipt.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.digitalreceipt.app.dataclass.RegisterUserDataClass
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_setting_page.*

class SettingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_page)

        var homebutton: AppCompatButton = findViewById(R.id.homebtn)
        /*var categorybutton: AppCompatButton = findViewById(R.id.categorybtn)*/
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

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("registerUser")
        val preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val value = it.getValue(RegisterUserDataClass::class.java)
                    if (value!!.userId == preferences.getString("user", null)!!.toInt()) {
                        usernametextview.text = value.userName
                        emailtextView.text = value.userEmail
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })



        var buttonaboutus: AppCompatButton = findViewById(R.id.aboutusbtn)
        var buttoncontactus: AppCompatButton = findViewById(R.id.contactusbtn)

        buttonaboutus.setOnClickListener {
            var intent: Intent = Intent(applicationContext, AboutUsPage::class.java)
            startActivity(intent)
        }

        buttoncontactus.setOnClickListener {
            var intent: Intent = Intent(applicationContext, ContactUsPage::class.java)
            startActivity(intent)
        }

    }
}