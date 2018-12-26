package com.wisilica.androdev.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.appcompat.R.id.add
import com.wisilica.androdev.R
import com.wisilica.androdev.ui.fragment.DetailFragment
import com.wisilica.androdev.utility.Utils

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        loadFragment()
    }

    fun loadFragment(){
         loadFragmentToActivity(DetailFragment(),false,R.id.frameLayout)
    }

    override fun registerListener() {

    }

    override fun initObjects() {

    }


}
