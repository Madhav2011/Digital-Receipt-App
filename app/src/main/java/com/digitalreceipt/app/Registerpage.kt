package com.digitalreceipt.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.digitalreceipt.app.dataclass.RegisterUserDataClass
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_registerpage.*


class Registerpage : AppCompatActivity() {
    companion object {
        var last = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registerpage)
        getLastId()
        btnregister.setOnClickListener {
            if (last != -1) {
                if (isUserRegister()) {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }

        clickheretext.setOnClickListener(View.OnClickListener() {
            var intent: Intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        })
    }

    private fun getLastId() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("registerUser")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                last = snapshot.childrenCount.toInt()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun isUserRegister(): Boolean {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("registerUser")
        var flag = false
        myRef.child((last+1).toString())
            .setValue(
                RegisterUserDataClass(
                    last+1,
                    editTextFullName.editText!!.text.toString(),
                    editTextEmail.editText!!.text.toString(),
                    editTextPassword.editText!!.text.toString()
                )
            )
            .addOnCompleteListener {
                if (it.isComplete) {
                    flag = true
                    Toast.makeText(this, "Registration Successfull", Toast.LENGTH_SHORT).show()
                    var intent: Intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                }
            }
        return flag
    }
}
