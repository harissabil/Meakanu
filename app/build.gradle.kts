plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.harissabil.meakanu"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.harissabil.meakanu"
        minSdk = 26
        targetSdk = 34
        versionCode = 21
        versionName = "1.0.20"
        ndkVersion = "26.1.10909125"
        ndk {
            debugSymbolLevel = "FULL"
        }

        val baseUrlTrefle: String =
            com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(
                rootDir
            ).getProperty("BASE_URL_TREFLE")
        val apiKeyTrefle: String =
            com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(
                rootDir
            ).getProperty("API_KEY_TREFLE")
        val baseUrlPlantnet: String =
            com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(
                rootDir
            ).getProperty("BASE_URL_PLANTNET")
        val apiKeyPlantnet: String =
            com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(
                rootDir
            ).getProperty("API_KEY_PLANTNET")
        val baseUrlNews: String =
            com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(
                rootDir
            ).getProperty("BASE_URL_NEWS")
        val apiKeyNews: String =
            com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(
                rootDir
            ).getProperty("API_KEY_NEWS")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL_TREFLE", baseUrlTrefle)
        buildConfigField(
            "String",
            "API_KEY_TREFLE",
            apiKeyTrefle
        )

        buildConfigField("String", "BASE_URL_PLANTNET", baseUrlPlantnet)
        buildConfigField("String", "API_KEY_PLANTNET", apiKeyPlantnet)

        buildConfigField("String", "BASE_URL_NEWS", baseUrlNews)
        buildConfigField("String", "API_KEY_NEWS", apiKeyNews)
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("jp.wasabeef:glide-transformations:4.3.0")
    //noinspection KaptUsageInsteadOfKsp
    kapt("com.github.bumptech.glide:compiler:4.16.0")
    implementation("com.github.bumptech.glide:annotations:4.16.0")

    // KTX
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // Coil
    implementation("io.coil-kt:coil:2.6.0")

    // Spinner
    implementation("com.github.skydoves:powerspinner:1.2.7")

    // Splashscreen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Paging 3
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")

    // uCrop
    implementation("com.github.Frosch2010:uCrop-n-Edit:3.0.4")

    // Browser
    implementation("androidx.browser:browser:1.8.0")
}