package com.example.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.model.UserResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<UserResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: UserResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTask(tasks: List<UserResponse>)

    @Update
    suspend fun updateTask(task: UserResponse)

    @Delete
    suspend fun deleteTask(task: UserResponse)
}