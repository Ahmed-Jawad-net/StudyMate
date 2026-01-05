plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.jawad.studymate"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.jawad.studymate"
        minSdk = 24
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Networking (Retrofit + Gson)
    implementation(libs.retrofit)
    // RecyclerView (if not already included)
    implementation(libs.recyclerview)
    implementation(libs.gson)



}