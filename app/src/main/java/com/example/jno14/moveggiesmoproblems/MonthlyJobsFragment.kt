package com.example.jno14.moveggiesmoproblems

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jno14.moveggiesmoproblems.R.id.month
import kotlinx.android.synthetic.main.fragment_monthly_jobs.*

class MonthlyJobsFragment : Fragment() {

    var adapter: TaskAdapter = TaskAdapter()

    companion object {
        fun newInstance(): MonthlyJobsFragment {
            return MonthlyJobsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView = fragment_task_list
        val layoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_monthly_jobs, container, false)
    }

//    fun filterTasks() {
//        val tasks =
//
//        when (month.toString()) {
//            "January" -> tasks?.filter { it.month == "January" }
//            "February" -> tasks?.filter { it.month == "February" }
//        }
//    }
}



