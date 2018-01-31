package com.example.jno14.moveggiesmoproblems.data

import android.arch.persistence.room.*
import android.content.Context
import java.io.Serializable

class TaskDatabase {

    companion object {
        private const val DB_NAME = "TaskDatabase"
        val instance = TaskDatabase()
    }

    lateinit var database: MyTaskDatabase

    fun init(context: Context) {
        database = Room.databaseBuilder(context, MyTaskDatabase::class.java, DB_NAME).build()
    }
}


@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
abstract class MyTaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

@Entity
data class Task(@ColumnInfo var description: String = "",
                @ColumnInfo var month: String = "",
                @PrimaryKey(autoGenerate = true) var id: Long? = null) : Serializable

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAll(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE FROM Task")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTask(task: Task) : Int
}