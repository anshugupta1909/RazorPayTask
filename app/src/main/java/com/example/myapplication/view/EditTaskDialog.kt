package com.example.myapplication.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myapplication.model.UserResponse

@Composable
fun EditTaskDialog(
    task: UserResponse,
    onDismiss: () -> Unit,
    onConfirm: (Int, String,Int) -> Unit
) {
    val title = remember { mutableStateOf(task.title.toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Task") },
        text = {
            Column {
                TextField(
                    value = title.value,
                    onValueChange = { newValue -> title.value = newValue  },
                    label = { Text("Title") }
                )

            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(task.id, title.value,task.userId) }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}