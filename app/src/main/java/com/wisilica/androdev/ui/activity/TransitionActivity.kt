package com.wisilica.androdev.ui.activity

import android.content.res.Configuration
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import com.wisilica.androdev.R
import kotlinx.android.synthetic.main.activity_transition.*


class TransitionActivity : AppCompatActivity() {
    var set =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)

        var cons1 =  ConstraintSet()
        var cons2 =  ConstraintSet()
        cons2.clone(this,R.layout.activity_home_large)
        cons1.clone(constraintlayoutmain)
        btnChange.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition(constraintlayoutmain)
                val constraint = if(set) cons1 else cons2
                constraint.applyTo(constraintlayoutmain)
                set = !set

            }
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        }else if (newConfig?.orientation == Configuration.ORIENTATION_PORTRAIT){

        }

    }
}
