package com.wisilica.androdev.ui.activity.Data

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.wisilica.androdev.R
import com.wisilica.androdev.ui.fragment.DataFragment
import com.wisilica.androdev.utility.onItemClickListener
import kotlinx.android.synthetic.main.activity_data.*

class DataActivity : AppCompatActivity() {

    var listener:onItemClickListener?=null
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        loadFragment()
        clickListener()

    }

    private fun clickListener() {
        btnOk.setOnClickListener{
            count++
            listener?.onClick("Clicked $count",20)
        }
    }

    private fun loadFragment() {
        val fragmentTransation = supportFragmentManager.beginTransaction()
        fragmentTransation.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransation.add(R.id.frameLayout, DataFragment.newInstance())
        fragmentTransation.commit()

    }

    public fun passData(listener: onItemClickListener){
        this.listener = listener
    }
}
