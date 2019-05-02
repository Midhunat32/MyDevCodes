package com.wisilica.androdev.ui.activity.tablayout


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wisilica.androdev.R
import com.wisilica.androdev.databinding.FragmentChildItemBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class ChildItemFragment : Fragment() {
    private var data:PagerData?=null
    private lateinit var binding: FragmentChildItemBinding

    companion object {
        fun newInstance(data:PagerData):ChildItemFragment{
            val fragment=  ChildItemFragment()
            val bundle = Bundle()
            bundle.putParcelable("DATABUNDLE",data)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_child_item,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundleData()
    }

    private fun getBundleData() {
        data  =arguments?.get("DATABUNDLE") as PagerData
        data?.title
        binding.pageData = data
    }
}
