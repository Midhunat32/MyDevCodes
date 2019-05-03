package com.wisilica.androdev.ui.activity.observer_pattern

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class UserDataRepository : MyObservable {

    private var data:DataObser?=null
    private var dataList:MutableList<DataObser> = arrayListOf()
    private val mObservers = arrayListOf<RepositoryObserver>()
    private var count=1

    companion object {
        fun newInstance(): UserDataRepository = UserDataRepository()
    }

    override fun registerObserver(repositoryObserver: RepositoryObserver) {
        if (!mObservers.contains(repositoryObserver)){
            mObservers.add(repositoryObserver)
        }
    }

    override fun removeObserver(repositoryObserver: RepositoryObserver) {
        if (mObservers.contains(repositoryObserver)){
            mObservers.remove(repositoryObserver)
        }
    }

    override fun notifyObservers() {
        for (item in mObservers){
            item.onObserverDataChanged(dataList)
        }
    }

    public fun getDataFromCloud(){
          for (i in count..count+10){
              dataList.add(DataObser("1000$i","DataObserver Member 1000$i"))
          }
          notifyObservers()
      }

    }
