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
    var count=12
    companion object {
        const val SPAN = 4
    }

    var myList: ArrayList<Words> = arrayListOf()

    internal lateinit var layoutManager: GridLayoutManager
    var adapter: RecyclerAdapterPuzzle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.wisilica.androdev.R.layout.activity_drag_drop)


         myArray = IntArray(SPAN)
        for (i in 1..SPAN) {
            myArray!![i - 1] = i
        }
        lcm = getLCM(myArray!!)

        initRecyclerView()

       val span = getNFactorial(SPAN).div(2)
        Log.d("FFFF",""+span)
    }


    private fun initRecyclerView() {
        layoutManager = GridLayoutManager(this,lcm!!)
        rvPuzzle.setHasFixedSize(true)
        adapter = RecyclerAdapterPuzzle(getData())

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                var total=myList.size
                var span =1
                var mode = total%SPAN
                val startPosition = (total) - mode;
                if(position>=startPosition){
                    if (mode!=0){
                        span = lcm?.div(mode) ?: lcm?.div(SPAN)!!
                    }else{
                        span = lcm?.div(SPAN)!!
                    }
                }else{
                   span = lcm?.div(SPAN)!!
                }
                return span
            }
        }

        rvPuzzle.layoutManager = layoutManager
        rvPuzzle.adapter = adapter
    }

    private fun getNFactorial(num: Int):Int{
        var factorial: Int = 1
        for (i in 1..num) {
            // factorial = factorial * i;
            factorial *= i
        }
        return factorial
    }




    private fun isLastReached(pos:Int):Boolean{
        val position = pos + 1
        val size = myList.size;
        val span = size % SPAN;
        if((size - position) <= span){
            return true
        }
        return false
    }

    private fun getSpan():Int{
        val size = myList.size;
        val span = size % SPAN;
        when(span){
            3 -> return 4
            2-> return 6
            1-> return 12
        }
        return 1

    }

    private fun getData():MutableList<Words>{
        var data:Words
        for (i in 0..count){
            data = Words("X"+(i+1),""+i+1)
            myList.add(data)
        }
        return myList
    }

    fun getLCM(numberArray: IntArray): Int {
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


