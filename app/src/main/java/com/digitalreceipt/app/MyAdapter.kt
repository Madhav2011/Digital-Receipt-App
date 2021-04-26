package com.digitalreceipt.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val productlist: List<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view:View = inflater.inflate(R.layout.items_view,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = productlist[position]
        holder.onBind(data)
    }

    override fun getItemCount(): Int {
        return productlist.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productname = itemView.findViewById<TextView>(R.id.productname)
        fun onBind(data: String) {
            productname.text = data
        }
    }
}
