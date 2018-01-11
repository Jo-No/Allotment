package com.example.jno14.moveggiesmoproblems

import android.widget.Spinner
import java.io.Serializable
import java.util.*

/**
 * Created by jno14 on 03/01/2018.
 */
data class Task(var description: String, var month: String, var completed: Boolean = false) : Serializable
