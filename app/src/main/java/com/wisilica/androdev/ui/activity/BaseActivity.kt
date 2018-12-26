package com.wisilica.androdev.ui.activity


import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.wisilica.androdev.R

public abstract class BaseActivity: AppCompatActivity(), View.OnClickListener{

    abstract fun registerListener()

    abstract fun initObjects()

    override fun onClick(v: View?) {

    }

    fun loadFragmentToActivity(fragment:Fragment,addToBackstack:Boolean,frameId :Int){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(frameId, fragment)
        if (addToBackstack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        }
        fragmentTransaction.commit()
    }



}