package com.wisilica.androdev.ui.activity.tablayout

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class MyPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    //private var list = listData
    private var list:List<PagerData>?=null
    private var data:PagerData?=null

    fun setData(list:MutableList<PagerData>){
        this.list =list
        notifyDataSetChanged()
    }

    override fun getItem(p0: Int): Fragment {
        data = this.list?.get(p0)
        return ChildItemFragment.newInstance(data!!)
    }

    override fun getCount(): Int {
        return list?.size?:0
    }
}