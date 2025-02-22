package com.example.myapplication.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.UserResponse

@Database(entities = [UserResponse::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}