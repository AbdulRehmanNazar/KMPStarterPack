import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    androidLibrary {
       namespace = "com.starter.app.shared"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()
    
       compilerOptions {
           jvmTarget = JvmTarget.JVM_11
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            //Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            //Ktor engine
            implementation(libs.ktor.client.okhttp) // Android engine

            implementation(libs.androidx.room.sqlite.wrapper)
        }
        iosMain.dependencies {
            //Ktor engine
            implementation(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
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
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)

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
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
//    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}