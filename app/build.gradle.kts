plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")  // Required for Hilt
    id("dagger.hilt.android.plugin")  // Hilt Plugin
    alias(libs.plugins.google.services) // Google services plugin
    alias(libs.plugins.firebase.crashlytics) // Crashlytics plugin
    alias(libs.plugins.firebase.perf) // Performance plugin
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.room.runtime)
    implementation(libs.ui)
    implementation(libs.androidx.material)
    implementation(libs.retrofit)
    implementation(libs.okhttp) // WebSocket support
    implementation(libs.converter.gson) // JSON conversion
    implementation ("com.squareup.okhttp3:okhttp:4.9.1")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")

    // Hilt for Jetpack Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.5.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")

    // Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    implementation("com.google.firebase:firebase-analytics-ktx:21.5.1")
    implementation("com.google.firebase:firebase-crashlytics-ktx:18.5.1")
    implementation("com.google.firebase:firebase-perf-ktx:20.4.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")

}