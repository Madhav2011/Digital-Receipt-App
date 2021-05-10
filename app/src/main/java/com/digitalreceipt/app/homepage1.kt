package com.digitalreceipt.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digitalreceipt.app.dataclass.NewProductDataClass
import com.digitalreceipt.app.dataclass.ProductList
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_homepage1.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class homepage1 : AppCompatActivity() {
    /* private val arrayList = ArrayList<String>()

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
         setContentView(R.layout.activity_homepage1)

         arrayList.add("Hello ONe")
         val myAdapter = MyAdapter(arrayList)
         val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
         recyclerView.apply {
             layoutManager = LinearLayoutManager(this@homepage1, RecyclerView.VERTICAL, false)
             adapter = myAdapter
     }
 }*/

    var selectedIndex = 0
    val sortingnames = arrayOf(
        "Newest Added", "Oldest Added", "Nearest Expiry Date"
    )

    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage1)
        getAllData()
        var homebutton: AppCompatButton = findViewById(R.id.homebtn)
        var categorybutton: AppCompatButton = findViewById(R.id.categorybtn)
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

        categorybutton.setOnClickListener {
            var intent: Intent = Intent(applicationContext, Categorypage::class.java)
            startActivityForResult(intent, 202)
        }
        settingbutton.setOnClickListener {
            var intent: Intent = Intent(applicationContext, SettingPage::class.java)
            startActivity(intent)
        }
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

    private fun getAllData(): ArrayList<ProductList> {
        val preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val database = FirebaseDatabase.getInstance()
        val myRef =
            database.getReference("BillData").child(preferences.getString("user", null)!!)
        val arrayList = ArrayList<ProductList>()
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val value = it.getValue(NewProductDataClass::class.java)
                    arrayList.add(
                        ProductList(
                            value!!.id,
                            name = value.name,
                            image = value.image,
                            date = value.expiryDate,
                            cat = value.category
                        )
                    )
                }
                adapter = MyAdapter()
                adapter.addAll(arrayList)
                recyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return arrayList
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(applicationContext).inflate(R.menu.logoutmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logoutmenu1) {
            var pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            var edit = pref.edit()
            edit.clear()
            edit.apply()
            var intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return true
    }

    fun SelectedItem(view: View) {
        var selectedItem = sortingnames[selectedIndex]
        val sortingdialog = MaterialAlertDialogBuilder(this)
        sortingdialog.setTitle("Sort By")
        sortingdialog.setSingleChoiceItems(sortingnames, selectedIndex) { dialog, which ->
            selectedIndex = which
            selectedItem = sortingnames[which]
        }
        sortingdialog.setPositiveButton("Confirm") { dialog, which ->
            if (selectedItem == "Newest Added") {
                newAdded(true)
            }
            if (selectedItem == "Oldest Added") {
                newAdded(false)
            }

            if (selectedItem == "Nearest Expiry Date") {
                sortByDate()
            }
        }
        sortingdialog.setNeutralButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        sortingdialog.show()
    }

    private fun sortByDate(): ArrayList<ProductList> {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = sdf.format(Date())
        val c = Calendar.getInstance()
        c.time = sdf.parse(currentDate)
        c.add(Calendar.DAY_OF_MONTH, 5)
        val endDate = sdf.format(c.time)
        val preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val database = FirebaseDatabase.getInstance()
        val myRef =
            database.getReference("BillData").child(preferences.getString("user", null)!!)
        var arrayList = ArrayList<ProductList>()
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val value = it.getValue(NewProductDataClass::class.java)
                    if (currentDate < value!!.expiryDate && endDate > value.expiryDate) {
                        arrayList.add(
                            ProductList(
                                value!!.id,
                                name = value.name,
                                image = value.image,
                                date = value.expiryDate,
                                cat = value.category
                            )
                        )
                    }
                }
                adapter = MyAdapter()
                adapter.addAll(arrayList)
                recyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return arrayList
    }

    private fun message(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun newAdded(isNewest: Boolean): ArrayList<ProductList> {
        val preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val database = FirebaseDatabase.getInstance()
        val myRef =
            database.getReference("BillData").child(preferences.getString("user", null)!!)
        var arrayList = ArrayList<ProductList>()
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val value = it.getValue(NewProductDataClass::class.java)
                    arrayList.add(
                        ProductList(
                            value!!.id,
                            name = value.name,
                            image = value.image,
                            date = value.expiryDate,
                            cat = value.category
                        )
                    )
                }
                if (isNewest) {
                    arrayList.sortByDescending { it.id }
                } else {
                    arrayList.sortedBy { it.id }
                }
                adapter = MyAdapter()
                adapter.addAll(arrayList)
                recyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return arrayList
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 202) {
            val cat = data!!.getStringExtra("cat")
            val preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            val database = FirebaseDatabase.getInstance()
            val myRef =
                database.getReference("BillData").child(preferences.getString("user", null)!!)
            var arrayList = ArrayList<ProductList>()
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        val value = it.getValue(NewProductDataClass::class.java)
                        if(value!!.category == cat) {
                            arrayList.add(
                                ProductList(
                                    value.id,
                                    name = value.name,
                                    image = value.image,
                                    date = value.expiryDate,
                                    cat = value.category
                                )
                            )
                        }
                    }
                    adapter = MyAdapter()
                    adapter.addAll(arrayList)
                    recyclerView.adapter = adapter
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }
    }
}
