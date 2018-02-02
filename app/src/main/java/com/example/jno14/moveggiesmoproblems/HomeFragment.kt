package com.example.jno14.moveggiesmoproblems

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
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
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date


class HomeFragment : Fragment() {

    val adapter: TaskAdapter = TaskAdapter()
    val presenter = HomePresenter(this)

    var formatter: DateFormat = SimpleDateFormat("MM")
    var dateNow = Date()

    var dateMonth = formatter.format(dateNow)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = home_list as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        lifecycle.addObserver(presenter)

        setTasks()
    }

    fun setTasks() {
        var monthNow =
        when (dateMonth){
            "01" -> "janurary"
            "02" -> "february"
            "03" -> "march"
            "04" -> "april"
            "05" -> "may"
            "06" -> "june"
            "07" -> "july"
            "08" -> "august"
            "09" -> "september"
            "10" -> "october"
            "11" -> "november"
            "12" -> "december"
            else -> {
                ""
            }
        }

        Thread({
            TaskRepository.instance
                    .getFlowable()
                    .map {
                        it.filter { it.month.toLowerCase() == monthNow }
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { homeTaskList: List<Task> ->
                                adapter.tasks = homeTaskList
                            },
                            { error ->
                                // TODO
                                throw error
                            }
                    )
        }).start()
    }

}

class HomePresenter(val view: HomeFragment, val repo: TaskRepository = TaskRepository.instance) : LifecycleObserver {

    private var disposable: Disposable? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        disposable = repo.getFlowable()
                .subscribe(
                        { success ->
                            view.setTasks()
                        },
                        { error -> throw error }
                )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        disposable?.dispose()
    }
}

//class HomePresenter(val view: HomeFragment, val repo: TaskRepository = TaskRepository.instance): LifecycleObserver {
//
//
//}

// Required empty public constructor
