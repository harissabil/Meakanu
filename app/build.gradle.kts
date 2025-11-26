import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.harissabil.meakanu"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.harissabil.meakanu"
        minSdk = 26
        targetSdk = 36
        versionCode = 24
        versionName = "1.1.0"
        ndkVersion = "29.0.14206865"
        ndk {
            debugSymbolLevel = "FULL"
        }


        val localProperties = Properties().apply {
            val file = rootProject.file("local.properties")
            if (file.exists()) {
                load(file.inputStream())
            }
        }

        fun getLocalProperty(name: String): String =
            localProperties.getProperty(name)
                ?: error("Property '$name' not found in local.properties")

        val baseUrlTrefle = getLocalProperty("BASE_URL_TREFLE")
        val apiKeyTrefle = getLocalProperty("API_KEY_TREFLE")
        val baseUrlPlantnet = getLocalProperty("BASE_URL_PLANTNET")
        val apiKeyPlantnet = getLocalProperty("API_KEY_PLANTNET")
        val baseUrlNews = getLocalProperty("BASE_URL_NEWS")
        val apiKeyNews = getLocalProperty("API_KEY_NEWS")

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

    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.10.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.9.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.9.6")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.10.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:5.0.5")
    implementation("jp.wasabeef:glide-transformations:4.3.0")
    //noinspection KaptUsageInsteadOfKsp
    kapt("com.github.bumptech.glide:compiler:5.0.5")
    implementation("com.github.bumptech.glide:annotations:5.0.5")

    // KTX
    implementation("androidx.room:room-ktx:2.8.4")
    implementation("androidx.activity:activity-ktx:1.12.0")
    implementation("androidx.fragment:fragment-ktx:1.8.9")
    implementation("androidx.recyclerview:recyclerview:1.4.0")

    // Room
    implementation("androidx.room:room-runtime:2.8.4")
    //noinspection KaptUsageInsteadOfKsp
    kapt("androidx.room:room-compiler:2.8.4")

    // Coil
    implementation("io.coil-kt:coil:2.7.0")

    // Spinner
    implementation("com.github.skydoves:powerspinner:1.2.7")

    // Splashscreen
    implementation("androidx.core:core-splashscreen:1.2.0")

    // Paging 3
    implementation("androidx.paging:paging-runtime-ktx:3.3.6")

    // uCrop
    implementation("com.github.jens-muenker:uCrop-n-Edit:4.1.1")

    // Browser
    implementation("androidx.browser:browser:1.9.0")
}