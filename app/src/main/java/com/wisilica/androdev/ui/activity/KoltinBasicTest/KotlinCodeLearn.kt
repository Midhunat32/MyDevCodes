package com.wisilica.androdev.ui.activity.KoltinBasicTest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wisilica.androdev.R

class KotlinCodeLearn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_code_learn)

        printFromInterface()
        val b = BaseImpl(10)
        Derived(b).printMe() // prints 10 :: accessing the printMe() method
    }

    interface Base {
        fun printMe() //abstract method
    }

    class BaseImpl(val x: Int) : Base {
        override fun printMe() { println(x) }   //implementation of the method
    }

    class Derived(b: Base) : Base by b



    private fun printFromInterface() {
        var programmer = object :Human{
            override fun think() {
                Toast.makeText(this@KotlinCodeLearn,"Test",Toast.LENGTH_LONG).show()
            }
        }
        programmer.think()
    }

    interface Human{
        fun think()
    }
}
