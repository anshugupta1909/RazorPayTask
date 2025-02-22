package com.example.myapplication.respository

import com.example.myapplication.database.dao.TaskDao
import com.example.myapplication.model.UserResponse
import com.example.myapplication.network.ApiInterface
import com.example.myapplication.network.ApiService
import com.example.myapplication.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val taskDao: TaskDao,
    private val apiInterface: ApiInterface
) {
    val tasks: Flow<List<UserResponse>> = taskDao.getAllTasks()

    suspend fun addTask(task: UserResponse) {
        taskDao.insertTask(task)
    }
    suspend fun addAllTask(taskList: List<UserResponse>) {
        taskDao.insertAllTask(taskList)
    }

    suspend fun updateTask(task: UserResponse) {
        taskDao.updateTask(task)
    }
    suspend fun deleteTask(task: UserResponse) {
        taskDao.deleteTask(task)
    }

    suspend fun fetchUserDataFromApi(): Resource<List<UserResponse>> {
        return try {
            val response = apiInterface.getUserData()
            if (response.isNotEmpty()) {
                taskDao.insertAllTask(response) // Save to Room
                Resource.Success(response)
            } else {
                Resource.Error("Empty response from API")
            }
        } catch (e: Exception) {
            Resource.Error("An error occurred: ${e.localizedMessage}")
        }
    }
}