package com.wisilica.androdev.ui.activity.observer_pattern

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.wisilica.androdev.R

class ObserverPatternActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observer_pattern)
        loadFragment()
    }

    private fun loadFragment() {
        val trasaction = supportFragmentManager.beginTransaction()
        trasaction.add(R.id.frameLayout,ObserverPatternFragment.newInstance())
        trasaction.commit()
    }

}
