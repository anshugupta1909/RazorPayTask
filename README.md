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
task_added    |When a new task is added
task_edited   |When an existing task is edited
task_completed|	When a task is marked as completed





