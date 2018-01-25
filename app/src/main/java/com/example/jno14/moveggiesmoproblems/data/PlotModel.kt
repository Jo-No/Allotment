package com.example.jno14.moveggiesmoproblems.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import java.io.Serializable

/**
 * Created by jno14 on 12/01/2018.
 */
data class Plot(@get:Exclude val key: String, var plotName: String, var plants: String) : Serializable


class PlotRepository {


    companion object {
        val instance by lazy {
            PlotRepository()
        }
    }

    private val subject = BehaviorSubject.create<List<Plot>>()

    init {

        FirebaseAuth.getInstance()
                .signInAnonymously()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        onLogin(it.result.user)
                    } else {
                        throw it.exception!!
                    }
                }


    }

    private var databaseRef: DatabaseReference? = null

    private fun onLogin(user: FirebaseUser) {
        databaseRef = FirebaseDatabase.getInstance()
                .getReference("users")
                .child(user.uid)

        databaseRef?.let { databaseReference ->
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    throw error.toException()
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val myList = snapshot.children.mapNotNull {
                        val key = it.key
                        (it.value as? HashMap<*, *>)?.let {
                            val plot = Plot(key, it["plants"] as String, it["plotName"] as String)
                            return@mapNotNull plot
                        }
                        return@mapNotNull null
                    }

                    subject.onNext(myList)
                }
            })
        }
    }


    fun getFlowable() : Flowable<List<Plot>> = subject.toFlowable(BackpressureStrategy.LATEST)


    fun addPlot(plot: Plot) {
        databaseRef?.let {
            it.push().setValue(plot)
        }
    }

    fun updatePlot(plot: Plot) {
        databaseRef?.let { databaseReference ->
            databaseReference.child(plot.key)
                    .setValue(plot)
        }
    }

    fun removePlot(plot: Plot) {
        databaseRef?.let { databaseReference ->
            databaseReference.child(plot.key).removeValue()
        }
    }

}