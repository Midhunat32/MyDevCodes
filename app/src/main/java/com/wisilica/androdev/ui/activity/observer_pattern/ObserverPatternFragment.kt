package com.wisilica.androdev.ui.activity.observer_pattern


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.wisilica.androdev.R
import kotlinx.android.synthetic.main.fragment_observer_pattern.*


/**
 * A simple [Fragment] subclass.
 *
 */
class ObserverPatternFragment : Fragment(),View.OnClickListener,RepositoryObserver {
    private var dataList:MutableList<DataObser> = arrayListOf();
    private var mUserRepository:UserDataRepository?=null
    private var resultData:String=""

    companion object{
         fun newInstance():ObserverPatternFragment = ObserverPatternFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_observer_pattern, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiObjects()
        registerObserver()
        registerListener()

    }

    private fun initiObjects() {
        mUserRepository = UserDataRepository.newInstance()
    }

    private fun registerListener() {
        btnObserver.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v){
            btnObserver->{
                loadDataUsingObserver()
            }
        }
    }



    private fun loadDataUsingObserver() {
        mUserRepository?.getDataFromCloud()
    }

    override fun onObserverDataChanged(data: Any?) {
        //here comes the data ..boom boom pow
        if (data is MutableList<*>){
            dataList = data as MutableList<DataObser>
            for (data1 in dataList){
                resultData = resultData+" "+data1.name+"\n"
                tvData.text = resultData
            }
        }
        Toast.makeText(activity,"Success",Toast.LENGTH_SHORT).show()
    }

    fun registerObserver(){
        mUserRepository?.registerObserver(this)
    }

}
