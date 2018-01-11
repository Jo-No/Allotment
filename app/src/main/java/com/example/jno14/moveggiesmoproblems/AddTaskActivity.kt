package com.example.jno14.moveggiesmoproblems

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val description = findViewById<EditText>(R.id.task_description)
        val month = findViewById<EditText>(R.id.month)
        val submit = findViewById<Button>(R.id.submit)

        submit.setOnClickListener {
            if (description.text?.toString().isNullOrBlank()) {
                description.error = "Please enter a description"
            } else if (description.text?.toString().isNullOrBlank()) {
                description.error = "Please enter a description"
            } else {
                val data = Intent()
                data.putExtra(MonthlyJobsActivity.DESCRIPTION_TEXT, description.text.toString())
                data.putExtra(MonthlyJobsActivity.MONTH_TEXT, month.text.toString())
                setResult(Activity.RESULT_OK, data)

                finish()
            }
        }
    }
}