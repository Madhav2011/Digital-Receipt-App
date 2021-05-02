package com.digitalreceipt.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView

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

class MyAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var detailsimage = intArrayOf(R.drawable.avenger,R.drawable.batman,R.drawable.flash,R.drawable.groot,R.drawable.hulk,R.drawable.avenger,R.drawable.batman,R.drawable.flash,R.drawable.groot,R.drawable.hulk)

    private var detailsprodname = arrayOf("Apple","Samsung","Nokia","LG","Motorola","Acer","Asus","Predator","Helios","Sony")

    private var detailsexpiry = arrayOf("1st-May-2020","2nd-May-2020","3rd-May-2020","4th-May-2020","5th-May-2020","6th-May-2020","7th-May-2020","8th-May-2020","9th-May-2020","10th-May-2020")

    private var detailscategory = arrayOf("Electronic","Furniture","Automobile","Household","Electronic","Furniture","Automobile","Household","Electronic","Furniture")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return detailsprodname.size
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.cardImage.setImageResource(detailsimage[position])
        holder.cardprodname.text = detailsprodname[position]
        holder.cardText2.text = detailsexpiry[position]
        holder.cardText3.text = detailscategory[position]
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var cardImage: ImageView
        var cardprodname: TextView
        var cardText2: TextView
        var cardText3: TextView

        init {
            cardImage = itemView.findViewById(R.id.imageViewincard)
            cardprodname = itemView.findViewById(R.id.productnameincard)
            cardText2 = itemView.findViewById(R.id.textView2incard)
            cardText3 = itemView.findViewById(R.id.textView3incard)
        }
    }
}










