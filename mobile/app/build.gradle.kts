plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.visually"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.visually"
        minSdk = 24
        targetSdk = 34
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
    buildFeatures {
        viewBinding = true
    }

    packagingOptions {
        // Exclude the META-INF/INDEX.LIST files from packaging
        exclude("META-INF/INDEX.LIST")
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/*.kotlin_module")
    }
}

dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.9.0")
    implementation("androidx.camera:camera-core:1.3.0-alpha06")
    implementation("androidx.camera:camera-camera2:1.3.0-alpha06")
    implementation("androidx.camera:camera-lifecycle:1.3.0-alpha06")
    implementation("androidx.camera:camera-video:1.3.0-alpha06")
    implementation("androidx.camera:camera-view:1.3.0-alpha06")
    implementation("androidx.camera:camera-mlkit-vision:1.3.0-alpha06")
    implementation("androidx.camera:camera-extensions:1.3.0-alpha06")
    implementation(platform("com.google.cloud:libraries-bom:26.37.0"))
    implementation("com.google.cloud:google-cloud-storage")
    implementation("com.google.auth:google-auth-library-oauth2-http:0.30.0")
    implementation("com.google.auth:google-auth-library-credentials:0.30.0")


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}