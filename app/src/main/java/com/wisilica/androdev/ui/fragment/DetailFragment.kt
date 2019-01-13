package com.wisilica.androdev.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.wisilica.androdev.R
import com.wisilica.androdev.ui.adapter.RecyclerAdapter
import com.wisilica.androdev.utility.Utils
import kotlinx.android.synthetic.main.base_recyclerview.*


class DetailFragment : BaseFragment(),Utils.recyclerClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerAdapter()
    }

    fun setRecyclerAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.hasFixedSize()
     //   RecyclerAdapterDevice().setData(getDataList(),this)
        recyclerView.adapter = RecyclerAdapter(getDataList(),this)

    }

    override fun registerListener() {

    }

    override fun initObjects() {

    }

    fun getDataList():ArrayList<String> {
        var arrayList = ArrayList<String>()
        arrayList.add("One")
        arrayList.add("Two")
        arrayList.add("Three")
        arrayList.add("Four")
        return arrayList
    }
    override fun onItemClick(view: View, position: Int, obj: Any?) {
       Toast.makeText(activity,""+obj.toString(),Toast.LENGTH_LONG)
    }


}
