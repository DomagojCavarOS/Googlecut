@file:Suppress("unused", "ClassName", "MemberVisibilityCanPrivate")

package dependencies

object config {
    val applicationId = "de.analyticom.newCometMobile"
    val testRunner = "androidx.testing.runner.AndroidJUnitRunner"

    private val major = 1
    private val minor = 0
    private val patch = 0
    private val build = 0 // bump for builds, public betas, etc.0
    val versionCode = major * 10000 + minor * 1000 + patch * 100 + build
    val versionName = "$major.$minor"
    val versionNameFull = "$versionName.$build"
}

object versions {
    val buildTools = "27.0.3"

    val compileSdk = 29
    val minSdk = 23
    val targetSdk = compileSdk

    //GOOGLECUT DEPS
    val androidxCore = "1.3.0"
    val nav = "2.3.0"
    val epoxy = "3.9.0"
    val retrofit = "2.9.0"
    val okhttp = "4.8.1"
    val glide = "4.11.0"
    val googlePlay = "17.1.0"
    val gradle = "4.1.1"
    val material = "1.1.0-alpha09"
    val kotlin = "1.3.72"
    val androidX = "1.1.0"
    val arch = "2.2.0"
    val androidTest = "1.0.2"
    val gson = "2.8.6"
    val koin = "2.1.6"
    val constraint = "1.1.3"
    val coroutines = "1.3.9"
    val room = "2.2.6"
}

object plugin {
    object android {
        val gradle = "com.android.tools.build:gradle:${versions.gradle}"
    }

    object kotlin {
        val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
        val runtime = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${versions.kotlin}"
    }
}

object deps {

    val kotlin = "orviewModelg.jetbrains.kotlin:kotlin-stdlib:${versions.kotlin}"
    val gson = "com.google.code.gson:gson:${versions.gson}"
    val glide = "com.github.bumptech.glide:glide:${versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${versions.glide}"

    object kotlinx {
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.coroutines}"
    }

    object room {
        val runtime = "androidx.room:room-runtime:${versions.room}"
        val compiler = "androidx.room:room-compiler:${versions.room}" //kapt
        val ktx = "androidx.room:room-ktx:${versions.room}"
        val testing = "androidx.room:room-testing:${versions.room}" //testImplementation

    }

    object arch {
        val extensions = "androidx.lifecycle:lifecycle-extensions:${versions.arch}"
        val viewmodel = "androidx.lifecycle:lifecycle-viewmodel:${versions.arch}"
    }

    object support {
        val design = "com.google.android.material:material:${versions.material}"
    }

    object androidX {
        val appCompat = "androidx.appcompat:appcompat:${versions.androidX}"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:${versions.constraint}"
        val recyclerView = "androidx.recyclerview:recyclerview:${versions.androidX}"
        val annotation = "androidx.annotation:annotation:${versions.androidX}"
        val navigation = "androidx.navigation:navigation-ui-ktx:${versions.nav}"
        val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${versions.nav}"
        val core = "androidx.core:core-ktx:${versions.androidxCore}"
    }

    object koin {
        val android = "org.koin:koin-android:${versions.koin}"
        val viewModel = "org.koin:koin-androidx-viewmodel:${versions.koin}"
    }

    object epoxy {
        val dep = "com.airbnb.android:epoxy:${versions.epoxy}"
        val annotation = "com.airbnb.android:epoxy-processor:${versions.epoxy}"
    }

    object googlePlay {
        val location = "com.google.android.gms:play-services-location:${versions.googlePlay}"
    }

    object squareup {
        val retrofit = "com.squareup.retrofit2:retrofit:${versions.retrofit}"
        val rxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava3:${versions.retrofit}"
        val converterGson = "com.squareup.retrofit2:converter-gson:${versions.retrofit}"
        val okhttp = "com.squareup.okhttp3:okhttp:${versions.okhttp}"
        val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${versions.okhttp}"
    }
}

object testing {
    val junit = "junit:junit:4.12"
    val rules = "com.android.support.test:rules:${versions.androidTest}"
    val runner = "com.android.support.test:runner:${versions.androidTest}"
}