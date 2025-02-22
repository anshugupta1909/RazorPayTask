package com.example.myapplication.network

import com.example.myapplication.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface ApiInterface {
    @GET("todos")
    suspend  fun getUserData(): List<UserResponse>

    @POST("tasks")
    suspend fun addTask(@Body task: UserResponse): UserResponse

    @PUT("tasks/{id}")
    suspend fun updateTask(@Path("id") id: String, @Body task: UserResponse): UserResponse
}