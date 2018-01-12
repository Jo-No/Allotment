package com.example.jno14.moveggiesmoproblems

import android.content.Context
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
//            newInstance(month)
//            var bundle: Bundle
//            bundle.putString(month) // specific fragment
//            var fragment: MonthlyJobsFragment = MonthlyJobsFragment( arguments = bundle)
            return MonthlyJobsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var tasks: MutableList<Task> = adapter.tasks
        val recyclerView = fragment_task_list
        val layoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
        return inflater!!.inflate(R.layout.fragment_monthly_jobs, container, false)
    }


//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//
//        val tasks = Storage.readData(context = MonthlyJobsActivity())
//
//        if (tasks != null && (adapter.tasks.isEmpty())) adapter.tasks = tasks
//    }

//    fun filterTasks() {
//        val tasks =
//
//        when (month.toString()) {
//            "January" -> tasks?.filter { it.month == "January" }
//            "February" -> tasks?.filter { it.month == "February" }
//        }
//    }
}

