package com.digitalreceipt.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.categoriescardview.view.*
import kotlinx.android.synthetic.main.row_cat.view.*
import kotlinx.android.synthetic.main.row_cat.view.textViewCat

class CatAdapter(private val callBack: (Int) -> Unit) :
    RecyclerView.Adapter<CatAdapter.ViewHolder>() {
    private val arrayList = ArrayList<String>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.categoriescardview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            categorynameincard.text = arrayList[position]
            categoriesconstraintincard.setOnClickListener {
                callBack.invoke(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    internal fun addAll(arrayList: ArrayList<String>) {
        this.arrayList.clear()
        this.arrayList.addAll(arrayList)
    }
}