package com.example.jno14.moveggiesmoproblems

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jno14.moveggiesmoproblems.data.Task
import com.example.jno14.moveggiesmoproblems.data.TaskRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_monthly_jobs.*

class MonthlyJobsFragment : Fragment() {

    private var adapter: TaskAdapter = TaskAdapter()

    private var presenter = TaskPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_monthly_jobs, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = monthly_task_list as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        adapter.listener = presenter

        lifecycle.addObserver(presenter)

        add_task_button?.setOnClickListener { _ ->
            val intent = Intent(context, AddTaskActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST)
        }
    }

    companion object {
        private val ADD_TASK_REQUEST = 0
        val DESCRIPTION_TEXT = "description"
        val MONTH_TEXT = "month"
    }

    fun setTasks(newList: List<Task>) {
        adapter.tasks = newList
    }

    fun editTasks(task: Task) {
        val intent = Intent(activity, AddTaskActivity::class.java)
        intent.putExtra("task", task)
        startActivity(intent)
    }

}

class TaskPresenter(val view: MonthlyJobsFragment, val repo: TaskRepository = TaskRepository.instance) : LifecycleObserver, OnButtonClickListener {
    override fun onButtonClicked(task: Task) {
        view.editTasks(task)
    }

    private var disposable: Disposable? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        disposable = repo.getFlowable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { success ->
                            view.setTasks(success)
                        },
                        { error -> throw error }
                )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        disposable?.dispose()
    }
}

//completedCheckBox.setOnCheckedChangeListener { _, isChecked ->
//    tasks[adapterPosition].completed = isChecked
//    if (task.completed) {
//        removeTask(task)
//    }
//}

