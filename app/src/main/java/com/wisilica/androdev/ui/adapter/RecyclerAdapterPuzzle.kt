package com.wisilica.androdev.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wisilica.androdev.R
import com.wisilica.androdev.model.Words

class RecyclerAdapterPuzzle(list:MutableList<Words>) :RecyclerView.Adapter<RecyclerAdapterPuzzle.ViewHolder>(){
    var list = list

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerAdapterPuzzle.ViewHolder {
       var v = LayoutInflater.from(p0.context).inflate(R.layout.rv_puzzle,p0,false)
        return  RecyclerAdapterPuzzle.ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvText: TextView =itemView.findViewById(R.id.tvText)
    }

    override fun onBindViewHolder(p0: ViewHolder, position: Int) {
        p0.tvText.text= list.get(position).letter
    }

    override fun getItemCount(): Int {
        return  list?.size
    }
}