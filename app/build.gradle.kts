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
        minSdk = 26
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
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.ui)
    implementation(libs.material3)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.runtime)
    implementation(libs.material)

    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.material)

    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)// "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2"
    implementation(libs.mockito.core.v531)// "org.mockito:mockito-core:5.3.1"
    implementation(libs.mockito.kotlin.v510)// "org.mockito.kotlin:mockito-kotlin:5.1.0"

    implementation(libs.kotlinx.serialization.json.v151) // org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1
    implementation(libs.retrofit2.kotlinx.serializationconverter) // com.jakewharton.retrofit:retrofit2-kotlinx-serializationconverter:1.0.0

    implementation(libs.retrofit2.retrofit.v290) // com.squareup.retrofit2:retrofit:2.9.0
    implementation(libs.converter.gson.v290) // com.squareup.retrofit2:converter-gson:2.9.0
    implementation(libs.okhttp.v493) // com.squareup.okhttp3:okhttp:4.9.3
    implementation(libs.logging.interceptor) // com.squareup.okhttp3:logging-interceptor:4.9.3

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}