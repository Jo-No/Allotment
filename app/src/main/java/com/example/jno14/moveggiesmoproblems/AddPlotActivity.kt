package com.example.jno14.moveggiesmoproblems

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_add_plot.*


class AddPlotActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plot_layout)

        val name = findViewById<EditText>(R.id.plot_name)
        val plant = findViewById<EditText>(R.id.plants)

        submit_plot.setOnClickListener{
            when {
                name.text?.toString().isNullOrBlank() -> name.error = "Please enter a description"
                plant.text?.toString().isNullOrBlank() -> plant.error = "Please enter a description"
                else -> {
                    val data = Intent()
                    data.putExtra(PlotLayoutFragment.PLOT_NAME, name.text.toString())
                    data.putExtra(PlotLayoutFragment.PLOT_PLANTS, plant.text.toString())
                    setResult(Activity.RESULT_OK, data)

                    finish()
                }
            }
        }
    }
}
