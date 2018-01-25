package com.example.jno14.moveggiesmoproblems

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner


class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val description = findViewById<EditText>(R.id.task_description)
        val month = findViewById<Spinner>(R.id.month)
        val submit = findViewById<Button>(R.id.submit)


        submit.setOnClickListener {
            if (description.text?.toString().isNullOrBlank()) {
                description.error = "Please enter a description"
            } else {
                val data = Intent()
                data.putExtra(MonthlyJobsFragment.DESCRIPTION_TEXT, description.text.toString())
                data.putExtra(MonthlyJobsFragment.MONTH_TEXT, month.selectedItem.toString())
                setResult(Activity.RESULT_OK, data)

                finish()
            }
        }
    }
}