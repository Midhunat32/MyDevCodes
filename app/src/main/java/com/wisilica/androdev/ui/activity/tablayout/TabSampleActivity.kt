package com.wisilica.androdev.ui.activity.tablayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wisilica.androdev.R

class TabSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_sample)
        loadFragment()
    }

    private fun loadFragment() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout,TabSampleFragment.newInstance())
        transaction.commit()
    }
}
