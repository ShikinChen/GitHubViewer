val compose_version = "1.0.0"

typealias and = Android
typealias dep = Dependencies

apply(rootProject.file("local.gradle.kts"))

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")

    kotlin("kapt")
    kotlin("plugin.serialization")
}

android {
    compileSdk = and.compileSdk
    buildToolsVersion = and.buildTools

    defaultConfig {
        applicationId = "me.shiki.githubviewer"
        minSdk = and.minSdk
        targetSdk = and.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        resValue("string", "github_user", findProperty("github_user").toString())
        resValue("string", "api_base_url", "https://api.github.com")
        buildConfigField("String", "GITHUB_TOKEN", findProperty("github_token").toString())
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            excludes.add("META-INF/AL2.0")
            excludes.add("META-INF/LGPL2.1")
        }
    }
}

dependencies {
    dep.kotlin.apply {
        implementation(serialization)
        implementation(kotlinxDatetime)
    }
    dep.androidx.apply {
        implementation(ktxCore)
        implementation(material)
        implementation(startup)
        implementation(browser)
    }
    dep.compose.apply {
        implementation(ui)
        implementation(material)
        implementation(toolingPreview)
        debugImplementation(tooling)
        implementation(constraint)
        implementation(layout)
        implementation(paging)
        implementation(icons)
        implementation(hiltNavigationCompose)
    }

    dep.accompanist.apply {
        implementation(insets)
        implementation(coil)
        implementation(swipeRefresh)
    }

    dep.hilt.apply {
        implementation(hiltAndroid)
        kapt(hiltCompiler)
        kapt(daggerHiltCompiler)
        kaptAndroidTest(daggerHiltCompiler)
    }

    dep.other.apply {
        implementation(androidAutoSize)
        implementation(mmkv)
        implementation(timber)
        implementation(loggingInterceptor)
    }

    dep.test.apply {
        // unit
        testImplementation(junit)
        testImplementation(junitExt)
        testImplementation(espresso)
        testImplementation(mockWebServer)
        testImplementation(mockitoCore)
        // compose
        androidTestImplementation(uiTestJunit4)
        debugImplementation(uiTestManifest)
    }

    dep.immersionBar.apply {
        implementation(core)
        implementation(ktx)
    }

    dep.objectBox.apply {
        releaseImplementation(android)
        debugImplementation(objectBoxBrowser)
    }

    dep.retrofit.apply {
        implementation(converterGson)
        implementation(retrofit2)
    }

    dep.okHttp.apply {
        implementation(core)
    }

    // implementation("androidx.appcompat:appcompat:1.2.0")
    // implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    // implementation("androidx.activity:activity-compose:1.3.0-alpha06")
}

apply {
    plugin("io.objectbox")
}