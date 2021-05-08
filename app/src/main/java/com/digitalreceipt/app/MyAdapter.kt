package com.digitalreceipt.app

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.digitalreceipt.app.dataclass.ProductList

/*
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
*/

class MyAdapter() : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private val arrayList = ArrayList<ProductList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(arrayList[position].image).into(holder.cardImage)
        holder.cardprodname.text = arrayList[position].name
        holder.cardText2.text = arrayList[position].date
        holder.cardText3.text = arrayList[position].cat

        holder.constraintincard.setOnClickListener {
            val intent = Intent(holder.itemView.context, productdetails::class.java)
            intent.putExtra("productId", arrayList[position].id)
            holder.itemView.context.startActivity(intent)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardImage: ImageView
        var cardprodname: TextView
        var cardText2: TextView
        var cardText3: TextView
        var constraintincard: ConstraintLayout

        init {
            cardImage = itemView.findViewById(R.id.imageViewincard)
            cardprodname = itemView.findViewById(R.id.productnameincard)
            cardText2 = itemView.findViewById(R.id.textView2incard)
            cardText3 = itemView.findViewById(R.id.textView3incard)
            constraintincard = itemView.findViewById(R.id.constraintincard)
        }
    }

    internal fun addAll(arrayList: ArrayList<ProductList>) {
        this.arrayList.clear()
        this.arrayList.addAll(arrayList)
    }
}










