package com.example.todo_app.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert
    fun addTask(taskEntity: TaskEntity)

    @Query("select * from TaskEntity where list=:name")
    fun getTaskByListName(name:String) : TaskEntity
}