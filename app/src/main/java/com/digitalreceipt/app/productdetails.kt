package com.digitalreceipt.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide
import com.digitalreceipt.app.dataclass.NewProductDataClass
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_homepage1.*
import kotlinx.android.synthetic.main.activity_productdetails.*

class productdetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productdetails)

        var buttonsave: AppCompatButton = findViewById(R.id.detailsbuttonAdd)

        buttonsave.setOnClickListener {
            Toast.makeText(this, "Receipt Saved Succesfully", Toast.LENGTH_SHORT).show()
            var intent: Intent = Intent(applicationContext,homepage1::class.java)
            startActivity(intent)
        }


        val intent = intent.getIntExtra("productId", -1)
        val preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val database = FirebaseDatabase.getInstance()
        val myRef =
            database.getReference("BillData").child(preferences.getString("user", null)!!)
                .child(intent.toString())
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue(NewProductDataClass::class.java)
                Glide.with(this@productdetails)
                    .load(value!!.image).into(detailsimageView2)

                detailsentprodname.editText!!.setText(value.name)
                detailsentdopname.editText!!.setText(value.dateOfPurchase)
                detailsentbillnum.editText!!.setText(value.billNumber)
                detailsentwarcardnum.editText!!.setText(value.warrentyCard)
                detailsentexprdate.editText!!.setText(value.expiryDate)
                detailsentnoshop.editText!!.setText(value.category)
                detailsentnameofshop.editText!!.setText(value.nameShop)
                detailsentupload.editText!!.setText(value.contact)
                detailsenttedst.editText!!.setText(value.address)

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}