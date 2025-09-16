import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            //Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            //Ktor engine
            implementation(libs.ktor.client.okhttp) // Android engine
        }

        iosMain.dependencies {
            //Ktor engine
            implementation(libs.ktor.client.darwin)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            //Voyager navigation and transition
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.transition)
            implementation(libs.voyager.tab.navigator)

            //Koin
            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.koin.compose.viewmodel)

            // Ktor
            implementation(libs.bundles.ktor)

            // Image loading - Kamel (works in commonMain for Android/iOS)
            implementation(libs.coil.image.loading)

            //Room
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            //SDP-SSP
            implementation(libs.sdp.ssp.compose.multiplatform)

            //Toast
            implementation(libs.cmptoast)

            // Key-Value Preferences Storage
            implementation(libs.multiplatform.settings)
            implementation(libs.multiplatform.settings.no.arg)

            //Ktor Monitor
            implementation(libs.ktor.monitor.logging)

            //Permissions
            implementation(libs.calf.permissions)

            //QRCode Scanning
            implementation(libs.easy.qr.scan)

            //Connectivity connection Status
            implementation(libs.konnection)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

android {
    namespace = "com.starter.app"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.starter.app"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
    add("kspAndroid", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}


