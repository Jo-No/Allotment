package com.example.jno14.moveggiesmoproblems

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jno14.moveggiesmoproblems.data.Plot
import com.example.jno14.moveggiesmoproblems.data.PlotDao
import com.example.jno14.moveggiesmoproblems.data.PlotDatabase
import com.example.jno14.moveggiesmoproblems.data.PlotRepository
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_plot_layout.*
import kotlinx.android.synthetic.main.plot.*

class PlotLayoutFragment : Fragment() {

    val adapter: PlotAdapter = PlotAdapter()

    private val presenter = PlotLayoutPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_plot_layout, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = recycler_plot_layout as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        adapter.listener = presenter

//        val plotDao: PlotDao = PlotDatabase.instance.database.plotDao()
//        plotDao.getAll()

        lifecycle.addObserver(presenter)

        add_plot_button.setOnClickListener {
            val intent = Intent(activity, AddPlotActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        val PLOT_NAME = "plotName"
        val PLOT_PLANTS = "plotPlants"
    }

    fun setPlotList(newList: List<Plot>) {
        adapter.plots = newList
    }

    fun startEditView(plot: Plot) {
        val intent = Intent(activity, AddPlotActivity::class.java)
        intent.putExtra("plot",plot)
        startActivity(intent)
    }
}


class PlotLayoutPresenter(val view: PlotLayoutFragment, val repo: PlotRepository = PlotRepository.instance) : LifecycleObserver, OnPlotClickedListener {
    override fun onPlotClicked(plot: Plot) {
        view.startEditView(plot)
    }

    private var disposable: Disposable? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        disposable = repo.getFlowable()
                .subscribe(
                        { success ->
                            view.setPlotList(success)
                        },
                        { error -> throw error }
                )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        disposable?.dispose()
    }
}

//recycler_plot_layout
