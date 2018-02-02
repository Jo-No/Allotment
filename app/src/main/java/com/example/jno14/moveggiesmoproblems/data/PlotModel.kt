package com.example.jno14.moveggiesmoproblems.data

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject


class PlotRepository(val plotDao: PlotDao = PlotDatabase.instance.database.plotDao()) {


    companion object {
        val instance by lazy {
            PlotRepository()
        }
    }

    private val subject = BehaviorSubject.create<List<Plot>>()

    fun getFlowable(): Flowable<List<Plot>> = subject.toFlowable(BackpressureStrategy.LATEST)


    private fun onDataChange() {
        val newListOfPlot = plotDao.getAll()
        subject.onNext(newListOfPlot)
    }


    fun addPlot(plot: Plot) {
        Thread({
            plotDao.addPlot(plot)
            onDataChange()
        }).start()
    }

    fun updatePlot(plot: Plot) {
        Thread({
            val rowChanged = plotDao.updatePlot(plot)
            if(rowChanged > 0){
                onDataChange()
            }
        }).start()
    }

    fun removePlot(plot: Plot) {
        Thread({
            plotDao.deletePlot(plot)
            onDataChange()
        }).start()
    }

    fun loadPlot(){
        Thread({
            onDataChange()
        }).start()
    }

}