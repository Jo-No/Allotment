package com.example.jno14.moveggiesmoproblems

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.bottom_navigation.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                addFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_plot_layout -> {
                addFragment(PlotLayoutFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_monthly_jobs -> {
                addFragment(MonthlyJobsFragment())
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

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }

}

//val intent = Intent(this, PlotLayoutActivity::class.java)
//startActivity(intent)
