package com.wisilica.androdev.ui.activity.tablayout


import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.wisilica.androdev.R

import android.support.v4.view.ViewPager
import com.wisilica.androdev.databinding.FragmentTabBinding
import com.wisilica.androdev.utility.ViewPagerTransform
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 *
 */
class TabSampleFragment : Fragment() {

    private lateinit var binding: FragmentTabBinding
    private var list = ArrayList<PagerData>()
    private var adapter:MyPagerAdapter?=null

    companion object {
        private var viewPager:ViewPager?=null
        fun newInstance():TabSampleFragment = TabSampleFragment()
        @JvmStatic
        @BindingAdapter("android:pagerAdapter")
        fun setViewPager(viewPager:ViewPager,adapter: MyPagerAdapter) {
            viewPager.adapter = adapter
            this.viewPager = viewPager
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab,container,false)
        adapter = MyPagerAdapter(activity?.supportFragmentManager)
        adapter?.setData(loadData())
        binding.pagerAdapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPagerTransform()
    }


    private fun setViewPagerTransform() {
        val density = resources.displayMetrics.density
        val partialWidth = Math.round(16 * density) // 16dp
        val pageMargin = Math.round(16 * density) // 8dp
        val viewPagerPadding = partialWidth + pageMargin
        viewPager?.setPadding(viewPagerPadding, 0, viewPagerPadding, 0)
        viewPager?.setPageTransformer(false, ViewPagerTransform())
    }

    private fun loadData():MutableList<PagerData> {
        for (i in 1..20){
            if(i%2==0){
                list.add(PagerData("Title $i",R.drawable.ic_rocket))
            }else{
                list.add(PagerData("Title $i",R.drawable.ic_cloud))
            }
        }
        return list
    }

}
