package com.example.jno14.moveggiesmoproblems

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class PlotLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plot_layout)

//        var addPlot = findViewById<FloatingActionButton>(R.id.add_plot_button)
//        addPlot.setOnClickListener { _ ->
//            addFragment(AddPlotFragment())
//        }
    }

//    private fun addFragment(fragment: AddPlotFragment) {
//        supportFragmentManager
//                .beginTransaction()
//                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
//                .add(R.id.activity_plot_layout, fragment, fragment.javaClass.simpleName)
//                .addToBackStack(fragment.javaClass.simpleName)
//                .commit()
//    }

}