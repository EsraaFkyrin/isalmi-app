package com.example.isalmiapp.radio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.isalmiapp.api.Radio


import com.example.isalmiapp.databinding.ItemRadioBinding

class RadioAdapter:RecyclerView.Adapter<RadioAdapter.ViewHolder>() {
   lateinit var viewBinding:ItemRadioBinding
   var channel= listOf<Radio>()
    var onItemClickPlay:OnItemClickListener?=null
    var onItemClickStop :OnItemClickListener?=null
    class ViewHolder(val viewBinding: ItemRadioBinding):RecyclerView.ViewHolder(viewBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       viewBinding=ItemRadioBinding.inflate(LayoutInflater.from(parent.context)
       ,parent,false
       )
      return ViewHolder(viewBinding)
    }

    fun changeData(data:List<Radio>){
        this.channel=data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return channel?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var items=channel?.get(position)

        holder.viewBinding.radioNameTv.text=items?.name

        onItemClickPlay.let {
            holder.viewBinding.icPlay.setOnClickListener {
                onItemClickPlay?.onItemClick(position,channel.get(position))
            }
        }

        onItemClickStop.let {
            holder.viewBinding.icStop.setOnClickListener {
                onItemClickPlay?.onItemClick(position,channel.get(position))
            }
        }

    }
  interface OnItemClickListener{
      fun onItemClick(position: Int,item:Radio)
  }

}