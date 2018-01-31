package com.example.jno14.moveggiesmoproblems

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jno14.moveggiesmoproblems.data.Task
import com.example.jno14.moveggiesmoproblems.data.TaskRepository
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity() {

    private var editTask: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        editTask = intent.extras?.getSerializable("task") as? Task

        editTask?.let { task ->
            edit_task_description.setText(task.description)
            edit_month.selectedItem.equals(task.month)
        }

        submit.setOnClickListener {
            when {
                edit_task_description.text?.toString().isNullOrBlank() -> edit_task_description.error = "Please enter a description"
                else -> {
                    val task = Task(description = edit_task_description.text.toString(), month = edit_month.selectedItem.toString(), id = editTask?.id)

                    if (editTask != null) {
                        TaskRepository.instance.updateTask(task)
                    } else {
                        TaskRepository.instance.addTask(task)
                    }

                    finish()
                }
            }
        }
    }
}
