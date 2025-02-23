package com.example.myapplication.viewModel

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.UserResponse
import com.example.myapplication.respository.UserRepository
import com.example.myapplication.utils.Resource
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val firebaseAnalytics: FirebaseAnalytics
): ViewModel() {

    var isLoading = mutableStateOf(false)
    init {
        viewModelScope.launch {
            fetchDataFromApi()
            observeUserData()
        }
    }

    private val _userData = MutableStateFlow<List<UserResponse>>(emptyList()) // ✅ StateFlow
    val userData: StateFlow<List<UserResponse>> = _userData.asStateFlow()



    // Fetch API data and store it in Room
    private fun fetchDataFromApi() {
        viewModelScope.launch {
            isLoading.value = true
            val result = userRepository.fetchUserDataFromApi()
            if (result is Resource.Error) {
                Log.e("UserViewModel", "API Error: ${result.message}")
            }
            isLoading.value = false
        }
    }
    // Observe Room database & update LiveData
    private fun observeUserData() {
        viewModelScope.launch {
            userRepository.tasks.collect { taskList ->
                _userData.value = taskList // ✅ Collect Room Flow
            }
        }
    }
    fun updateTask(task: UserResponse) {
        viewModelScope.launch {
            userRepository.updateTask(task)
            val bundle = Bundle().apply {
                putString("task_title", task.title)
            }
            firebaseAnalytics.logEvent("task_updated", bundle)
        }
    }

    fun markTaskCompleted(task: UserResponse) {
        viewModelScope.launch {
            userRepository.updateTask(task.copy(isCompleted = !task.isCompleted))
            val bundle = Bundle().apply {
                putString("task_title", task.title)
                putString("completed", task.isCompleted.toString())
            }
            firebaseAnalytics.logEvent("task_completed", bundle)
        }
    }

    fun deleteTask(task: UserResponse) {
        viewModelScope.launch {
            userRepository.deleteTask(task)
            val bundle = Bundle().apply {
                putString("task_title", task.title)
            }
            firebaseAnalytics.logEvent("task_deleted", bundle)
        }
    }
    fun addTask(task: UserResponse) {
        viewModelScope.launch {
            userRepository.addTask(task)
            // ✅ Log Firebase Event
            val bundle = Bundle().apply {
                putString("task_title", task.title)
            }
            firebaseAnalytics.logEvent("task_added", bundle)
        }
    }
}