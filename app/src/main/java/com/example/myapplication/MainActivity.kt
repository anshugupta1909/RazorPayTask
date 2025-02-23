package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.model.UserResponse
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.Resource
import com.example.myapplication.view.AddTaskDialog
import com.example.myapplication.view.EditTaskDialog
import com.example.myapplication.view.UserListItem
import com.example.myapplication.viewModel.UserViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint



@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            MyApplicationTheme {
                UserScreen()

            }
        }
    }
}



@Composable
fun UserScreen(viewModel: UserViewModel = hiltViewModel()) {
    val users = viewModel.userData.collectAsState()
    val isLoading = viewModel.isLoading.value
    val selectedTask = rememberSaveable { mutableStateOf<UserResponse?>(null) }
    val showDialogEdit = rememberSaveable{ mutableStateOf(false) }
    val showDialogAdd = rememberSaveable { mutableStateOf(false) }
        Scaffold(

                   // contentWindowInsets = WindowInsets.navigationBars,
                  floatingActionButton = {
                  FloatingActionButton(onClick = { showDialogAdd.value = true }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Task")
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .windowInsetsPadding(WindowInsets.systemBars) // ✅ Fix system bar overlap
                    .padding(padding)
            ) {
                Text(text = "Task List", fontSize = 24.sp, fontWeight = FontWeight.Bold)


                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                else if (users.value.isEmpty()) {
                    Text(
                        text = "No users available",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    LazyColumn (
                        modifier = Modifier.weight(1f)
                    ){
                        items(users.value) { user ->
                            UserListItem(
                                user,
                                onEdit = { selectedTask.value = it; showDialogEdit.value = true },
                                onDelete=viewModel::deleteTask,
                                onToggleComplete = { viewModel.markTaskCompleted(user) }
                            )
                        }
                    }

                    if (showDialogEdit.value) {
                        selectedTask.value?.let {
                            EditTaskDialog(
                                task = it,
                                onDismiss = { showDialogEdit.value = false },
                                onConfirm = { id, title, useRId ->
                                    viewModel.updateTask(
                                        UserResponse(
                                            id = id,
                                            title = title,
                                            userId = useRId,
                                            isCompleted = false
                                        )
                                    )
                                    showDialogEdit.value = false
                                }

                            )
                        }
                    }
                }
                if (showDialogAdd.value) {
                    AddTaskDialog(viewModel, onDismiss = { showDialogAdd.value = false })
                }


            }
        }

}



