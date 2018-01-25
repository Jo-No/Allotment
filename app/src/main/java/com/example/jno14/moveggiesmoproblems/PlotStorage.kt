package com.example.jno14.moveggiesmoproblems

/**
 * Created by jno14 on 15/01/2018.
 */
import android.content.Context
import android.util.Log
import com.example.jno14.moveggiesmoproblems.data.Plot
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object PlotStorage {

    private val LOG_TAG = PlotStorage::class.java.simpleName
    private val FILE_NAME = "plot_list.ser"

    fun writeData(context: Context, plots: List<Plot>) {
        var fos: FileOutputStream? = null
        var oos: ObjectOutputStream? = null

        try {
            // Open file and write list
            fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            oos = ObjectOutputStream(fos)
            oos.writeObject(plots)
        } catch (e: Exception) {
            Log.e(LOG_TAG, "Could not write to file.")
            e.printStackTrace()
        } finally {
            try {
                oos?.close()
                fos?.close()
            } catch (e: Exception) {
                Log.e(LOG_TAG, "Could not close the file.")
                e.printStackTrace()
            }

        }
    }
    fun readData(context: Context): List<Plot>? {
        var fis: FileInputStream? = null
        var ois: ObjectInputStream? = null

        var plots: List<Plot>? = ArrayList()

        try {
            // Open file and read list
            fis = context.openFileInput(FILE_NAME)
            ois = ObjectInputStream(fis)

            plots = ois?.readObject() as? List<Plot>
        } catch (e: Exception) {
            Log.e(LOG_TAG, "Could not read from file.")
            e.printStackTrace()
        } finally {
            try {
                ois?.close()
                fis?.close()
            } catch (e: Exception) {
                Log.e(LOG_TAG, "Could not close the file.")
                e.printStackTrace()
            }

        }

        return plots
    }
}