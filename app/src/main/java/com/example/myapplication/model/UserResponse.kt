package com.example.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class UserResponse(
    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val isCompleted: Boolean
)
