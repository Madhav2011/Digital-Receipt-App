package com.digitalreceipt.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_homepage1.*
import kotlin.jvm.internal.Ref

class  homepage1 : AppCompatActivity() {
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

    var selectedIndex=0
    val sortingnames= arrayOf(
        "Newest Added","Oldest Added","Nearest Expiry Date"
    )

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MyAdapter.ViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage1)


        var homebutton:AppCompatButton = findViewById(R.id.homebtn)
        var sortbutton:AppCompatButton = findViewById(R.id.sortbtn)
        var categorybutton:AppCompatButton = findViewById(R.id.categorybtn)
        var settingbutton:AppCompatButton = findViewById(R.id.settingbtn)
        var floatingaddicon: FloatingActionButton = findViewById(R.id.addiconbottom)


        floatingaddicon.setOnClickListener {
            var intent: Intent = Intent(applicationContext, ProductForm::class.java)
            startActivity(intent)
        }

        homebutton.setOnClickListener {
            var intent: Intent = Intent(applicationContext,homepage1::class.java)
            startActivity(intent)
        }

        categorybutton.setOnClickListener {
            var intent: Intent = Intent(applicationContext,Categorypage::class.java)
            startActivity(intent)
        }

        settingbutton.setOnClickListener {
            var intent: Intent = Intent(applicationContext,SettingPage::class.java)
            startActivity(intent)
        }

        layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager

        adapter = MyAdapter()
        recyclerView.adapter = adapter

}

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            MenuInflater(applicationContext).inflate(R.menu.logoutmenu,menu)
            return true
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.logoutmenu1)
        {
            var pref=getSharedPreferences("MyPref",Context.MODE_PRIVATE)
            var edit=pref.edit()
            edit.clear()
            edit.commit()
            var intent=Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return true
    }

    fun SelectedItem(view: View) {
        var selectedItem = sortingnames[selectedIndex]
        val sortingdialog = MaterialAlertDialogBuilder(this)
        sortingdialog.setTitle("Sort By")
        sortingdialog.setSingleChoiceItems(sortingnames,selectedIndex){
            dialog,which->
             selectedIndex = which
            selectedItem = sortingnames[which]
        }
        sortingdialog.setPositiveButton("Confirm"){
            dialog, which ->
            message("$selectedItem is Selected")
        }
        sortingdialog.setNeutralButton("Cancel"){
            dialog, which ->
            dialog.dismiss()
        }
        sortingdialog.show()
    }
    private fun message(msg:String){
        Toast.makeText(this, msg,Toast.LENGTH_LONG).show()
    }


}
