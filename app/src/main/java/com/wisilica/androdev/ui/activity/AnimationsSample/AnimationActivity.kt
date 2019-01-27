package com.wisilica.androdev.ui.activity.AnimationsSample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_animation.*
import android.app.Dialog
import android.view.Window
import kotlinx.android.synthetic.main.dilaog_anim.*
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.ViewAnimationUtils
import android.R
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.content.DialogInterface




class AnimationActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.wisilica.androdev.R.layout.activity_animation)

        registerListener()

    }

    private fun registerListener() {
        btnDialog.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btnDialog -> {
                circularDialogAnim()
            }
        }
    }


    private fun circularDialogAnim() {
        val dialogView = View.inflate(this, com.wisilica.androdev.R.layout.dilaog_anim, null)
        val dialog = Dialog(this,R.style.DeviceDefault_ButtonBar_AlertDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogView)


        dialog.setOnShowListener {
            revealShow( dialogView,true, null)
        }

        dialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }




    private fun revealShow(dialogView:View,showFlag: Boolean, dialog1: Dialog?) {
        val w = dialogView.getWidth()
        val h = dialogView.getHeight()
        val endRadius = Math.hypot(w.toDouble(), h.toDouble()).toFloat()
        var cx:Int = btnDialog.getX().toInt() + (btnDialog.getWidth() / 2).toInt()
        var cy:Int = btnDialog.getY().toInt() + (btnDialog.getHeight()).toInt() + 56
        if (showFlag) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val revealAnimator = ViewAnimationUtils.createCircularReveal(dialogView, cx, cy,
                0f, endRadius.toFloat())
                dialogView.setVisibility(View.VISIBLE)
                revealAnimator.duration =600
                revealAnimator.start()
            }
        }
    }
}
