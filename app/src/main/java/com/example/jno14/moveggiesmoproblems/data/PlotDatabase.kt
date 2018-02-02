package com.example.jno14.moveggiesmoproblems.data

import android.arch.persistence.room.*
import android.content.Context
import android.media.Image
import java.io.Serializable

class PlotDatabase {

    companion object {
        private const val DB_NAME = "PlotDatabase"
        val instance = PlotDatabase()
    }

    lateinit var database: MyDatabase

    fun init(context: Context) {
        database = Room.databaseBuilder(context, MyDatabase::class.java, DB_NAME).build()
    }

}


@Database(entities = arrayOf(Plot::class), version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun plotDao(): PlotDao
}

@Entity
data class Plot(@ColumnInfo var plotName: String = "",
                @ColumnInfo var plants: String = "",
                @PrimaryKey(autoGenerate = true) var id: Long? = null) : Serializable

@Dao
interface PlotDao {
    @Query("SELECT * FROM Plot")
    fun getAll(): List<Plot>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlot(plot: Plot)

    @Delete
    fun deletePlot(plot: Plot)

    @Query("DELETE FROM Plot")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePlot(plot: Plot) : Int
}