package com.digitalreceipt.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.digitalreceipt.app.dataclass.RegisterUserDataClass
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var entUname: TextInputLayout
    lateinit var entUpass: TextInputLayout
    lateinit var btnLogin: AppCompatButton
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
        entUname = findViewById(R.id.entusername)
        entUpass = findViewById(R.id.entpassword)
        btnLogin = findViewById(R.id.btlogin)
        btnLogin.setOnClickListener {
            var flag = true
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("registerUser")
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        val value = it.getValue(RegisterUserDataClass::class.java)
                        if (entusername.editText!!.text.toString() == value!!.userEmail &&
                            entpassword.editText!!.text.toString() == value!!.userPassword
                        ) {
                            flag = false
                            val preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                            val editor = preferences.edit()
                            editor.putString("user", value.userId.toString())
                            editor.apply()
                            val intent: Intent = Intent(applicationContext, homepage1::class.java)
                            startActivity(intent)
                            finish()
                            return@forEach
                        }
                    }
                    if (flag) {
                        Toast.makeText(this@MainActivity, "Invalid username or password, try again", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })

        }
    }
}