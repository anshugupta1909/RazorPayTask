RazorPay Assignment

Tech Stack:
Programming Language: Kotlin
UI Framework: Jetpack Compose
Architecture: MVVM (ViewModel, Repository, StateFlow)
Networking: Retrofit + Coroutines
Local Storage: Room Database
Dependency Injection:  Hilt

API:
Mock API used:"https://jsonplaceholder.typicode.com/todos"
Base URL:"https://jsonplaceholder.typicode.com/"
End point used to fetch list from : "todos"

Project Structure:
📦 App
 ┣ 📂 app/src/main/java/com/example/myapplication
 ┃ ┣ 📂 model          # Data Models (UserResponse)
 ┃ ┣ 📂 viewModel      # ViewModel for handling UI logic
 ┃ ┣ 📂 repository     # Repository for data handling
 ┃ ┣ 📂 network        # API Service (Retrofit)
 ┃ ┣ 📂 database       # Room Database & DAO
 ┃ ┣ 📂 view           # Composable UI Components (itemUI, Dialogs)
 ┃ ┣ 📂 di             # Dependency Injection(Module)
 ┃ ┣ 📜 MainActivity.kt  # Entry Point of App
 ┃ ┗ 📜 UserApplication.kt  # Hilt Application Class

Dependencies:
dependencies {

  // MVVM Components
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

     // Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")


     // Firebase
    implementation("com.google.firebase:firebase-analytics-ktx:21.5.1")
    implementation("com.google.firebase:firebase-crashlytics-ktx:18.5.1")
    implementation("com.google.firebase:firebase-perf-ktx:20.4.0")

    // Dependency Injection
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")

    // Retrofit (Networking)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
}


The following Firebase Analytics events are tracked:

Event Name    |Trigger Condition
task_added    -When a new task is added
task_updated  -When an existing task is edited
task_completed-	When a task is marked as completed

List UI which contain Edit, Delete , checkbox for Task Completed, Trigger Crash Button to trigger crash
<img width="351" alt="image" src="https://github.com/user-attachments/assets/647c0b6b-38a3-43aa-ab23-182bb8e5a4f1" />


Steps to trigger crash
1. click on Trigger crash
app will crashed and send log to firebase

You can see in Crash Analytics in Firebase:
<img width="1412" alt="image" src="https://github.com/user-attachments/assets/66ced389-c647-4af1-a5c9-f340482bbb8f" />

Steps: To edit task
1. click on Edit Icon
2.Dialog come and edit text and save it
<img width="351" alt="image" src="https://github.com/user-attachments/assets/8eaa9d69-61a8-4415-824a-dd50fa8ae195" />

Firebase Event
task_updated event show in firebase
<img width="1157" alt="image" src="https://github.com/user-attachments/assets/aba8d05c-66de-4b7a-a275-be64ea3d41f5" />















