package com.example.jno14.moveggiesmoproblems.data

import android.widget.Spinner
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import java.io.Serializable
import java.util.*

class TaskRepository(val taskDao: TaskDao = TaskDatabase.instance.database.taskDao()) {

    companion object {
        val instance by lazy {
            TaskRepository()
        }
    }

    private val subject = BehaviorSubject.create<List<Task>>()

    fun getFlowable(): Flowable<List<Task>> = subject.toFlowable(BackpressureStrategy.LATEST)

    private fun onDataChange() {
        val newListOfTasks = taskDao.getAll()
        subject.onNext(newListOfTasks)
    }


    fun addTask(task: Task) {
        Thread({
            taskDao.addTask(task)
            onDataChange()
        }).start()
    }

    fun updateTask(task: Task) {
        Thread({
            val rowChanged = taskDao.updateTask(task)
            if(rowChanged > 0){
                onDataChange()
            }
        }).start()
    }

    fun removeTask(task: Task) {
        Thread({
            taskDao.deleteTask(task)
            onDataChange()
        }).start()
    }


}