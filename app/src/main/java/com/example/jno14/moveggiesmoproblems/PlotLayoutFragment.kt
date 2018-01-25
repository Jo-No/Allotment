package com.example.jno14.moveggiesmoproblems

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.example.jno14.moveggiesmoproblems.data.Plot
import com.example.jno14.moveggiesmoproblems.data.PlotRepository
import kotlinx.android.synthetic.main.fragment_plot_layout.*
import kotlinx.android.synthetic.main.plot.*

class PlotLayoutFragment : Fragment() {

    var adapter: PlotAdapter = PlotAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repo = PlotRepository.instance

        val recyclerView = recycler_plot_layout as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_plot_layout, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val plot = Plot("key", data?.getStringExtra(PlotLayoutFragment.PLOT_NAME).orEmpty(), data?.getStringExtra(PlotLayoutFragment.PLOT_PLANTS).orEmpty())
        adapter.addNewPlot(plot)
    }

    override fun onResume() {
        super.onResume()

        val plots = PlotRepository.instance.getFlowable()

        if (plots != null) adapter.plots = plots

    }

    companion object {
        val PLOT_NAME = "plotName"
        val PLOT_PLANTS = "plotPlants"
    }
}


//recycler_plot_layout