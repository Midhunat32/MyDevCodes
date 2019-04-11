package com.wisilica.androdev.ui.activity.ThreadpoolExecutor

import android.util.Log

class TaskOne : Runnable {
    override fun run() {
        for (i in  0..10000000000000000){
            Log.d("RUNNNNNN",""+i)
        }
    }

    fun loadData(){
        run()
    }

}