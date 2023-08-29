plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.harissabil.meakanu"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.harissabil.meakanu"
        minSdk = 26
        targetSdk = 33
        versionCode = 6
        versionName = "1.0.5"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL_TREFLE", "\"https://trefle.io/api/v1/\"")
        buildConfigField(
            "String",
            "API_KEY_TREFLE",
            "\"PqklXosE29uCKj71w4d4fmUtpJAAcP0tI8H4j2O3fuM\""
        )

        buildConfigField("String", "BASE_URL_PLANTNET", "\"https://my-api.plantnet.org/v2/\"")
        buildConfigField("String", "API_KEY_PLANTNET", "\"2b10Qhs7Lxsa11Uuuy6Jhyd0k\"")

        buildConfigField("String", "BASE_URL_NEWS", "\"https://newsapi.org/v2/\"")
        buildConfigField("String", "API_KEY_NEWS", "\"2a53338ab65f4ab181e7be37a0b5e7d6\"")
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

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.15.0")
    implementation("jp.wasabeef:glide-transformations:4.3.0")
    //noinspection KaptUsageInsteadOfKsp
    kapt("com.github.bumptech.glide:compiler:4.15.0")
    implementation("com.github.bumptech.glide:annotations:4.15.0")

    // KTX
    implementation("androidx.room:room-ktx:2.5.2")
    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("androidx.fragment:fragment-ktx:1.6.1")
    implementation("androidx.recyclerview:recyclerview:1.3.1-rc01")

    // Room
    implementation("androidx.room:room-runtime:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")

    // Coil
    implementation("io.coil-kt:coil:2.4.0")

    // Spinner
    implementation("com.github.skydoves:powerspinner:1.2.7")

    // Splashscreen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Paging 3
    implementation("androidx.paging:paging-runtime-ktx:3.2.0")

    // uCrop
    implementation("com.github.Frosch2010:uCrop-n-Edit:3.0.4")
}