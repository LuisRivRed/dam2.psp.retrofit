import org.jetbrains.kotlin.psi.stubs.impl.serialize

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.psp.retrofit"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.psp.retrofit"
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //retrofit
    implementation (libs.retrofit)
    //implementation (libs.retrofit2.xuse)
    //annotations: GET, POST
    implementation (libs.logging.interceptor)
    implementation(libs.converter.gson)
    implementation (libs.kotlinx.coroutines)
    //serializable
    implementation(libs.kotlinx.serialization)
    //jakeWharton
    implementation(libs.jakewharton)
    //okhttp3
    implementation(libs.okhttp3)
    //test:mockito
    implementation(libs.mockito)
    implementation(libs.mockito.kotlin)
    implementation(libs.mockito.jetbrains)
}