package com.example.jno14.moveggiesmoproblems

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView

/**
 * Created by jno14 on 15/01/2018.
 */
class PlotAdapter (plots: MutableList<Plot> = ArrayList()) : RecyclerView.Adapter<PlotAdapter.PlotViewHolder>() {

    var plots: MutableList<Plot> = plots
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: PlotAdapter.PlotViewHolder?, position: Int) {
        holder?.bindPlot(plots[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PlotAdapter.PlotViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return plots.size
    }

    inner class PlotViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
        val plotTextView = view?.findViewById<View>(R.id.plot) as TextView
        val plantsTextView = view?.findViewById<View>(R.id.plants) as TextView

        fun bindPlot(plot: Plot) {
            plotTextView.text = plot.plotName
            plantsTextView.text = plot.plants
        }
    }
}