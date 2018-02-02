package com.example.jno14.moveggiesmoproblems

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jno14.moveggiesmoproblems.R.id.image
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

//            plot.image =
//                    when (plot.plants.toLowerCase()){
//                        "tomato", "aubergine", "chilli" -> R.drawable.tomato_image
//                        "potato" -> R.drawable.potato_image
//                        "peas" -> R.drawable.pea_image
//                        "roots", "onions", "parsnip", "carrot" -> R.drawable.carrot_image
//                        "squashes", "courgette" -> R.drawable.squash_image
//                        else -> {
//                            R.drawable.lettuce_image
//                        }
//                    }
        }
    }
}

interface OnPlotClickedListener {
    fun onPlotClicked(plot: Plot)
}