package com.example.jno14.moveggiesmoproblems

import android.app.Application
import com.example.jno14.moveggiesmoproblems.data.TaskDatabase

class TaskApp: Application() {

    override fun onCreate() {
        super.onCreate()
        TaskDatabase.instance.init(this)
    }

}