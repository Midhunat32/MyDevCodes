package com.wisilica.androdev.utility

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View

public class Utils{

    interface recyclerClickListener{
        fun onItemClick(view: View,position:Int,obj:Any?)
    }

    interface dbListener{
        fun dbInsertionSuccess();
        fun dbInsertionFailed();
        fun dbFetchFailed();
    }

}