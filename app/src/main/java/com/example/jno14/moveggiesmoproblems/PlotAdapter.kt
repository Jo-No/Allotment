package com.example.jno14.moveggiesmoproblems

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jno14.moveggiesmoproblems.data.Plot
import kotlinx.android.synthetic.main.plot.view.*

class PlotAdapter : RecyclerView.Adapter<PlotAdapter.PlotViewHolder>() {

    var plots: List<Plot> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener : OnPlotClickedListener? = null

    override fun onBindViewHolder(holder: PlotAdapter.PlotViewHolder, position: Int) {
        holder.bindPlot(plots[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PlotAdapter.PlotViewHolder {
        val context = parent?.context
        val view = LayoutInflater.from(context).inflate(R.layout.plot, parent, false)
        return PlotViewHolder(view)
    }

    override fun getItemCount(): Int {
        return plots.size
    }

    inner class PlotViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val plotNameTextView = view.plot
        val plantsTextView = view.plants

        fun bindPlot(plot: Plot) {
            view.setOnClickListener {
                listener?.onPlotClicked(plot)
            }

            plotNameTextView.text = plot.plotName
            plantsTextView.text = plot.plants
        }

    }

}

interface OnPlotClickedListener {
    fun onPlotClicked(plot: Plot)
}