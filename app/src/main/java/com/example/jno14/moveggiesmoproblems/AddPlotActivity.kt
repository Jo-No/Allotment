package com.example.jno14.moveggiesmoproblems

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jno14.moveggiesmoproblems.data.Plot
import com.example.jno14.moveggiesmoproblems.data.PlotRepository
import kotlinx.android.synthetic.main.activity_add_plot.*
import kotlinx.android.synthetic.main.plot.*


class AddPlotActivity : AppCompatActivity() {

    private var editPlot: Plot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plot)

        editPlot = intent.extras?.getSerializable("plot") as? Plot

        editPlot?.let { plot ->
            plot_name.setText(plot.plotName)
            plot_plants.setText(plot.plants)
//            height_spinner.setText(plot.height)
//            width_spinner.setText(plot.width)
        }

        submit_plot.setOnClickListener {
            when {
                plot_name.text?.toString().isNullOrBlank() -> plot_name.error = "Please enter a name for this plot"
                plot_plants.text?.toString().isNullOrBlank() -> plot_plants.error = "Please enter some plants"
                else -> {

                    val plot = Plot(plotName = plot_name.text.toString(), plants = plot_plants.text.toString(), id = editPlot?.id)
//                    width = width_spinner.text.toString().toInt(),
                    if (editPlot != null) {
                        PlotRepository.instance.updatePlot(plot)
                    } else {
                        PlotRepository.instance.addPlot(plot)
                    }

                    finish()
                }
            }
        }
    }
}
//height = height_spinner.text.toString().toInt(),