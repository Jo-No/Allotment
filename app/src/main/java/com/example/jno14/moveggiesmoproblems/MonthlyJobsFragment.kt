package com.example.jno14.moveggiesmoproblems

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_monthly_jobs.*

class MonthlyJobsFragment: Fragment() {

    private var adapter: TaskAdapter = TaskAdapter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val inflatedView = inflater?.inflate(R.layout.fragment_monthly_jobs, container, false)
        return inflatedView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = monthly_task_list as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        var addTaskButton: Button? = view?.findViewById(R.id.add_task_button)
        addTaskButton?.setOnClickListener { _ ->
            val intent = Intent(context, AddTaskActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST)
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

        val tasks = TaskStorage.readData(activity.applicationContext)

        if (tasks != null && (adapter.tasks.isEmpty())) adapter.tasks = tasks
    }

    override fun onPause() {
        super.onPause()

        TaskStorage.writeData(context, adapter.tasks)
    }

    companion object {
        private val ADD_TASK_REQUEST = 0
        val DESCRIPTION_TEXT = "description"
        val MONTH_TEXT = "month"
    }

}



//Taken from onCreate
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
