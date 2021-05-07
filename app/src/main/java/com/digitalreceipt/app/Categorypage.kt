package com.digitalreceipt.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digitalreceipt.app.dataclass.NewProductDataClass
import com.digitalreceipt.app.dataclass.ProductList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_categorypage.*
import kotlinx.android.synthetic.main.activity_homepage1.*

class Categorypage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorypage)

        val arrayList = ArrayList<String>()
        val preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val database = FirebaseDatabase.getInstance()
        val myRef =
            database.getReference("BillData").child(preferences.getString("user", null)!!)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val value = it.getValue(NewProductDataClass::class.java)
                        arrayList.add(value!!.nameShop)
                }
                val catAdapter = CatAdapter(){
                    val intent = Intent(this@Categorypage, homepage1::class.java)
                    intent.putExtra("cat", arrayList[it])
                    setResult(202, intent)
                    finish()
                }
                catAdapter.addAll(arrayList)
                recyclerViewCat.apply {
                    layoutManager = LinearLayoutManager(this@Categorypage, RecyclerView.VERTICAL, false)
                    adapter = catAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}