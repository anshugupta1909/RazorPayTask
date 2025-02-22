package com.example.myapplication.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myapplication.model.UserResponse
import com.example.myapplication.viewModel.UserViewModel
import java.util.UUID
import kotlin.random.Random

@Composable
fun AddTaskDialog(viewModel: UserViewModel, onDismiss: () -> Unit) {
    var title = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Task") },
        text = {
            Column {
                TextField(value = title.value, onValueChange = { title.value = it }, label = { Text("Title") })
            }
        },
        confirmButton = {
            Button(onClick = {
                if (title.value.isNotBlank()) {
                    viewModel.addTask(UserResponse(id=(99..1000).random(),title = title.value, userId = (1..9999).random(), isCompleted = false))
                    onDismiss()
                }
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}