package com.example.jno14.moveggiesmoproblems

import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jno14.moveggiesmoproblems.R.id.add
import com.example.jno14.moveggiesmoproblems.R.id.fragment

class PlotLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plot_layout)

    }
//
//    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
//        val fragmentTransaction = beginTransaction()
//        fragmentTransaction.func()
//        fragmentTransaction.commit()
//
//    }
}