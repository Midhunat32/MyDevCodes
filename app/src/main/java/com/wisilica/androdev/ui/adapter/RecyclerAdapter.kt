package com.wisilica.androdev.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wisilica.androdev.R
import com.wisilica.androdev.utility.Utils

class RecyclerAdapter(list2: ArrayList<String>,listener2: Utils.recyclerClickListener) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    var lists:ArrayList<String> = list2
    var listener: Utils.recyclerClickListener? = listener2

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var v = LayoutInflater.from(p0.context).inflate(R.layout.rv_item, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int=lists.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.tvItemName.text= lists[p1]

        p0.tvItemName.setOnClickListener{
            listener?.onItemClick(p0.tvItemName,p1,lists?.get(p1))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemName:TextView=itemView.findViewById(R.id.tvItemName)

    }

}