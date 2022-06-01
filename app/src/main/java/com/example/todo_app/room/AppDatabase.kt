package com.example.todo_app.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ListEntity::class,TaskEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun listDao(): ListDao
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(AppDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movie_database"
                ).allowMainThreadQueries()
                .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}