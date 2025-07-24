plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.foodapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.foodapp"
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
                getDefaultProguardFile("proguard-android-optimize.txt") ,
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
//for circuler image
    implementation("de.hdodenhof:circleimageview:3.1.0")
    //for roomDB
    implementation("androidx.room:room-runtime:2.7.2")
    annotationProcessor("androidx.room:room-compiler:2.7.2")
//for livedata and viewmodel
    implementation("androidx.lifecycle:lifecycle-livedata:2.9.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.9.2")
}