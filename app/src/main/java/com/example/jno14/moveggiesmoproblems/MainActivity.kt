package com.example.jno14.moveggiesmoproblems

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.jno14.moveggiesmoproblems.R.layout.bottom_navigation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_navigation.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                addHomeFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_plot_layout -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_monthly_jobs -> {
                addJobsFragment(MonthlyJobsFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

//    private fun addFragment(fragment: Fragment) {
//        supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.content, fragment, fragment.javaClass.simpleName)
//                .addToBackStack(fragment.javaClass.simpleName)
//                .commit()
//    }

    private fun addJobsFragment(fragment: MonthlyJobsFragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }

    private fun addHomeFragment(fragment: HomeFragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }


}

//val intent = Intent(this, PlotLayoutActivity::class.java)
//startActivity(intent)
