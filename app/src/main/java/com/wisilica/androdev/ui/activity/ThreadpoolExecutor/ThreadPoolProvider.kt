package com.wisilica.androdev.ui.activity.ThreadpoolExecutor

import android.os.Process
import android.support.annotation.NonNull
import android.util.Log
import org.jetbrains.annotations.NotNull
import java.util.concurrent.*

 object ThreadPoolProvider {

     private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()
     private val mMainThreadExecutor: Executor?=null

     var mBackgroundTask: ThreadPoolExecutor =
         ThreadPoolExecutor(
             NUMBER_OF_CORES*2,
             NUMBER_OF_CORES*2,
             60L,
             TimeUnit.SECONDS,
             LinkedBlockingQueue(),
             BackgroundThreadFactory())


     private class BackgroundThreadFactory: ThreadFactory {
         override fun newThread(r: Runnable?): Thread {
             val thread = Thread(r)
             thread.name="CustomThread"
             thread.priority = Process.THREAD_PRIORITY_BACKGROUND
             thread.setUncaughtExceptionHandler { thread, exception ->
                 Log.e("ERRROR",""+thread.name+" "+exception.message)
             }
             return thread
         }
     }

     fun executeOnBackground(@NonNull runnable:Runnable){
         this.mBackgroundTask?.execute(runnable)
     }

     fun getInstanceThreadExecutor():ExecutorService{
         var executeService = Executors.newFixedThreadPool(ThreadPoolProvider.NUMBER_OF_CORES)
         return executeService
     }

}