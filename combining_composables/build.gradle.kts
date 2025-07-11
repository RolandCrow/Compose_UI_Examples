plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.combining_composables"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.combining_composables"
        minSdk = 26
        targetSdk = 36
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
    // Compose
    implementation(libs.androidx.runtime)
    implementation(libs.ui)
    implementation(libs.androidx.foundation.layout)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.ui.tooling)
    implementation(libs.androidx.compiler)
    // SplashScreen compat library
    implementation(libs.androidx.core.splashscreen)
    // Navigation
    implementation (libs.androidx.navigation.compose)

    // Room
    implementation (libs.androidx.room.ktx)
    implementation (libs.androidx.room.runtime)
    ksp (libs.androidx.room.compiler)

    // Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)

    // Flow x LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Unit Tests
    testImplementation (libs.junit)

    // Instrumented Tests
    androidTestImplementation (libs.androidx.runner)
    androidTestImplementation (libs.androidx.junit.v114)
}