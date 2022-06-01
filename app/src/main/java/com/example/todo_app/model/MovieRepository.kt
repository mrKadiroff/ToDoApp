package com.example.todo_app.model

import androidx.lifecycle.LiveData
import com.example.todo_app.room.ListDao
import com.example.todo_app.room.ListEntity

class MovieRepository(private val listDao: ListDao) {
    val allMovies: LiveData<List<ListEntity>> = listDao.getAll()

    suspend fun insert(list: ListEntity) {
        listDao.insert(list)
    }
}