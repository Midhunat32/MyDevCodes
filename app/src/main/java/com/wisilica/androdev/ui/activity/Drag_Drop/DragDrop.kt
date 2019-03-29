package com.wisilica.androdev.ui.activity.Drag_Drop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.wisilica.androdev.model.Words
import com.wisilica.androdev.ui.adapter.RecyclerAdapterPuzzle
import kotlinx.android.synthetic.main.activity_drag_drop.*


class DragDrop : AppCompatActivity() {
    var myArray:IntArray? = null
    var lcm:Int? = null
    var count=18
    companion object {
        const val SPAN = 4
    }

    var myList: ArrayList<Words> = arrayListOf()

    internal lateinit var layoutManager: GridLayoutManager
    var adapter: RecyclerAdapterPuzzle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.wisilica.androdev.R.layout.activity_drag_drop)

        createArray()
        initRecyclerView()
        checkKotlin()
    }

    private fun checkKotlin() {
        val mArrayInt = arrayOf(123,321,"123")
        val mArrayString = arrayOf("1","2","3")

        for (i in mArrayInt){
            println("Value Int $i")
        }

        for (j in mArrayString){
            println("Value String $j")
        }

        for (k in mArrayString.indices) {
            println("mArrayString[$k] " + mArrayString[k])
        }

        val function:(String,String)->String={org,portal->"$org develop $portal"}
        myFun("sssit.org","javatpoint.com",function)

    }

    fun myFun(org: String,portal: String, fn: (String,String) -> String): Unit {
        val result = fn(org,portal)
        println(result)
    }


    private fun createArray() {
        myArray = IntArray(SPAN)
        for (i in 1..SPAN) {
            myArray!![i - 1] = i
        }
        lcm = getLCM(myArray!!)
    }

    private fun initRecyclerView() {
        layoutManager = GridLayoutManager(this,lcm!!)
        rvPuzzle.setHasFixedSize(true)
        adapter = RecyclerAdapterPuzzle(getData())

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                var total=myList.size
                var mode = total%SPAN
                val startPosition = (total) - mode;
                var span =lcm?.div(SPAN)!!
                if(position>=startPosition){
                    if (mode!=0){
                        span = lcm?.div(mode) ?: lcm?.div(SPAN)!!
                    }
                }
                return span
            }
        }
        rvPuzzle.layoutManager = layoutManager
        rvPuzzle.adapter = adapter
    }



    private fun getData():MutableList<Words>{
        var data:Words
        for (i in 0..count){
            data = Words(""+(i+1),""+i+1)
            myList.add(data)
        }
        return myList
    }

    private fun getLCM(numberArray: IntArray): Int {
        var lcm: Int = 1
        var divisor = 2
        while (true) {
            var cnt = 0
            var divisible = false
            for (i in 0 until numberArray.size) {
                if (numberArray[i] === 0) {
                    return 0
                } else if (numberArray[i] < 0) {
                    numberArray[i] = numberArray[i] * -1
                }
                if (numberArray[i] === 1) {
                    cnt++
                }
                if (numberArray[i] % divisor === 0) {
                    divisible = true
                    numberArray[i] = numberArray[i] / divisor
                }
            }

            if (divisible) {
                lcm = lcm * divisor
            } else {
                divisor++
            }
            if (cnt == numberArray.size) {
                return lcm
            }
        }
    }



}


