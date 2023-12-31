package com.example.isalmiapp.hadeth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.isalmiapp.R


class AdapterHadeth(var items: List<String>) :
    RecyclerView.Adapter<AdapterHadeth.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.txt_view_item_hadeth)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater
            .from(parent.context).inflate(R.layout.item_recylerview_name_hadeth, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var itemview=items[position]
        holder.name.setText(itemview)



        if(OnHadethClickListner!=null)
        {
            holder.name.setOnClickListener{
                OnHadethClickListner?.OnItemClick(position, items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    var OnHadethClickListner: OnItemClickListner?=null

    interface OnItemClickListner{
        fun OnItemClick(position: Int, HadethName: String)
    }
}