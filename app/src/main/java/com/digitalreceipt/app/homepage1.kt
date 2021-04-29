package com.digitalreceipt.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class  homepage1 : AppCompatActivity() {
    private val arrayList = ArrayList<String>()

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
}
