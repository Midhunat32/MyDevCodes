package com.wisilica.androdev.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.wisilica.androdev.R
import com.wisilica.androdev.ui.activity.Data.DataActivity
import com.wisilica.androdev.utility.onItemClickListener
import kotlinx.android.synthetic.main.fragment_data.*


/**
 * A simple [Fragment] subclass.
 *
 */
class DataFragment : Fragment() {

    companion object Factory{
        fun newInstance():DataFragment = DataFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_data, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onShowData()

    }

    private fun onShowData() {
            (activity as DataActivity).passData(object :onItemClickListener{
                override fun onClick(item: Any, position: Int) {
                    tvShow.text = (item as String)
                }

            })
    }






}
