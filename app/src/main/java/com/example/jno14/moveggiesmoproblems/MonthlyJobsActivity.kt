package com.example.jno14.moveggiesmoproblems

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.example.jno14.moveggiesmoproblems.R.id.task_list
import kotlinx.android.synthetic.main.content_activity_monthly_jobs.*

class MonthlyJobsActivity : AppCompatActivity() {

    var adapter: TaskAdapter = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monthly_jobs)

//        val spinner = findViewById<Spinner>(R.id.months_spinner)
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                val monthSelected = parent.getItemAtPosition(position).toString()
//                if (monthSelected.equals("")) {
//                } else {
//                    TaskAdapter.performSorting(monthSelected)
//                }
//            }
//          override fun onNothingSelected(parent: AdapterView<*>) {
//            }
//        }
//        fun performSorting(monthSelected: String){
//            //get spinner pos
//            tasks.sortedBy { it.position.first }
//            //reload
//        }
//        val dataAdapter = ArrayAdapter.createFromResource(this, R.array.months_array, android.R.layout.simple_spinner_item)
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter = dataAdapter

        val recyclerView = task_list as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { _ ->
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST)
//            addFragment(MonthlyJobsFragment())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            val task = Task(data?.getStringExtra(DESCRIPTION_TEXT).orEmpty(), data?.getStringExtra(MONTH_TEXT).orEmpty())
            adapter.addTask(task)
        }
    }

    override fun onResume(){
        super.onResume()

        val tasks = Storage.readData(this)

        if (tasks != null && (adapter.tasks.isEmpty())) adapter.tasks = tasks
    }

    override fun onPause() {
        super.onPause()

        Storage.writeData(this, adapter.tasks)
    }

    companion object {
        private val ADD_TASK_REQUEST = 0
        val DESCRIPTION_TEXT = "description"
        val MONTH_TEXT = "month"
    }

    private fun addFragment(fragment: MonthlyJobsFragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .add(R.id.monthly_jobs_layout, fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }

}