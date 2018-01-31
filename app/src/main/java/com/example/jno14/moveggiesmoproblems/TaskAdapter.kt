package com.example.jno14.moveggiesmoproblems

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jno14.moveggiesmoproblems.data.Task
import kotlinx.android.synthetic.main.task.view.*


class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var tasks: List<Task> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: OnButtonClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TaskViewHolder {
        val context = parent?.context
        val view = LayoutInflater.from(context).inflate(R.layout.task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder?, position: Int) {
        holder?.bindTask(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    inner class TaskViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val descriptionTextView = view.task_description
        val monthTextView = view.month

        fun bindTask(task: Task) {
            descriptionTextView.text = task.description
            monthTextView.text = task.month

            view.setOnClickListener{
                listener?.onButtonClicked(task)
            }
        }
    }
}

interface OnButtonClickListener {
    fun onButtonClicked(task: Task)
}

