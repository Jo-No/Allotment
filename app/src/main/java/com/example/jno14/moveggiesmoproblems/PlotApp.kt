package com.example.jno14.moveggiesmoproblems

import android.app.Application
import com.example.jno14.moveggiesmoproblems.data.PlotDatabase

/**
 * Created by jno14 on 26/01/2018.
 */
class PlotApp : Application() {

    override fun onCreate() {
        super.onCreate()
        PlotDatabase.instance.init(this)
    }

}