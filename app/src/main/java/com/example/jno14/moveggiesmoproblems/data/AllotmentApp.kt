package com.example.jno14.moveggiesmoproblems.data

import android.app.Application
import com.example.jno14.moveggiesmoproblems.data.PlotDatabase
import com.example.jno14.moveggiesmoproblems.data.TaskDatabase

class AllotmentApp : Application() {

    override fun onCreate() {
        super.onCreate()
        PlotDatabase.instance.init(this)
        TaskDatabase.instance.init(this)
    }

}