package com.example.jno14.moveggiesmoproblems

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.TextView
import com.example.jno14.moveggiesmoproblems.R.id.month

class TaskAdapter(tasks: MutableList<Task> = ArrayList()) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var tasks: MutableList<Task> = tasks
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TaskViewHolder {
        val context = parent?.context
        val view = LayoutInflater.from(context)?.inflate(R.layout.task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder?, position: Int) {
        holder?.bindTask(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    inner class TaskViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
        val descriptionTextView = view?.findViewById<View>(R.id.task_description) as TextView
        val monthTextView = view?.findViewById<View>(R.id.month) as TextView
        val completedCheckBox = view?.findViewById<View>(R.id.task_completed) as CheckBox

        fun bindTask(task: Task) {
            descriptionTextView.text = task.description
            monthTextView.text = task.month
            completedCheckBox.isChecked = task.completed

            completedCheckBox.setOnCheckedChangeListener { _, isChecked ->
                tasks[adapterPosition].completed = isChecked
                if (task.completed) {
                    removeTask(task)
                }
            }
        }
    }

//    fun performSorting(monthSelected: String){
//        //get spinner pos
//        var monthSelected = monthSelected
//        tasks.sortBy { it.month. }
//
//        //reload
//    }

//    fun performFiltering(monthSelected: String){
//        for (i in (1..tasks.size)) {
//            var taskItem = tasks[i]
//            if(taskItem.month.equals(monthSelected)){
//            }
//        }
//    }

    fun removeTask(task: Task){
        tasks.remove(task)
    }

    fun addTask(task: Task) {
        tasks.add(task)
        notifyDataSetChanged()
    }
}