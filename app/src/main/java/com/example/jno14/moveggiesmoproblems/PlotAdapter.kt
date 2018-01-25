package com.example.jno14.moveggiesmoproblems

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jno14.moveggiesmoproblems.data.Plot
import com.example.jno14.moveggiesmoproblems.data.PlotRepository
import io.reactivex.Flowable

/**
 * Created by jno14 on 15/01/2018.
 */
class PlotAdapter (plots: Flowable<List<Plot>>) : RecyclerView.Adapter<PlotAdapter.PlotViewHolder>() {

    var plots: Flowable<List<Plot>> = plots
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: PlotAdapter.PlotViewHolder?, position: Int) {
        holder?.bindPlot(Plot("","","")) //TODO: might be wrong
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PlotAdapter.PlotViewHolder {
        var context = parent?.context
        var view = LayoutInflater.from(context)?.inflate(R.layout.plot, parent, false)
        return PlotViewHolder(view)
    }

    override fun getItemCount(): Int {
        return plots.count() as Int
    }

    inner class PlotViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
//        val plotTextView = view?.findViewById<View>(R.id.plot) as TextView
//        val plantsTextView = view?.findViewById<View>(R.id.plants) as TextView
        val plotNameTextView = view?.findViewById<View>(R.id.plot) as TextView
        val plantsTextView = view?.findViewById<View>(R.id.plants) as TextView

        fun bindPlot(plot: Plot) {
//            plotTextView.text = plot.plotName
//            plantsTextView.text = plot.plants
            plotNameTextView.text = plot.plotName
            plantsTextView.text = plot.plants
        }
    }



    fun addNewPlot(plot: Plot){
        PlotRepository.instance.addPlot(plot)
    }
}