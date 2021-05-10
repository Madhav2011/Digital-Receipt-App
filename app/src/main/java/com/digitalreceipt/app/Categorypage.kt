package com.digitalreceipt.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digitalreceipt.app.dataclass.NewProductDataClass
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        val arrayList = ArrayList<String>()
        val preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val database = FirebaseDatabase.getInstance()
        val myRef =
            database.getReference("BillData").child(preferences.getString("user", null)!!)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val value = it.getValue(NewProductDataClass::class.java)
                    if (!arrayList.contains(value!!.category))
                        arrayList.add(value.category)
                }
                val catAdapter = CatAdapter {
                    val intent = Intent(this@Categorypage, homepage1::class.java)
                    intent.putExtra("cat", arrayList[it])
                    setResult(202, intent)
                    finish()
                }
                catAdapter.addAll(arrayList)
                recyclerViewCat.apply {
                    layoutManager =
                        LinearLayoutManager(this@Categorypage, RecyclerView.VERTICAL, false)
                    adapter = catAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}