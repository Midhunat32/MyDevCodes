package com.wisilica.androdev.ui.activity.ThreadpoolExecutor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.wisilica.androdev.R
import java.util.concurrent.Executors

class ThreadPool : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_pool)

//        ThreadPoolProvider.getInstanceThreadExecutor().execute {
//            val task = TaskOne()
//            task.loadData()
//        }

        ThreadPoolProvider.executeOnBackground(TaskOne())


    }
}
