// build.gradle.kts file
plugins {
    id("com.android.application") version "8.7.3"
    id("org.jetbrains.kotlin.android") version "2.0.0"
}

android {
    namespace = "com.psp.retrofit"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.psp.retrofit"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx.v1150)
    implementation(libs.androidx.appcompat.v161)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v121)
    androidTestImplementation(libs.androidx.espresso.core.v361)

    implementation(libs.kotlinx.coroutines.android)

    // Retrofit
    implementation(libs.retrofit.v2110)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit2.kotlinx.serialization.converter.v080)

    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization.json.v151)

    // Guava
    implementation(libs.guava)

    implementation(libs.gson)
    implementation(libs.converter.gson)

}
