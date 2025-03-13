import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.native.cocoapods)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
        freeCompilerArgs.add("-Xcontext-receivers")
    }

    explicitApi()

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    val xcframeworkName = "BugsnagKMP"
    val xcf = XCFramework(xcframeworkName)

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { target ->
        target.binaries.framework {
            baseName = xcframeworkName
            binaryOption("bundleId", "com.bugsnag.cocoa.$xcframeworkName")
            xcf.add(this)
            isStatic = true
        }
    }

    js(IR) {
        useEsModules()
        browser {
            commonWebpackConfig {
                mode = KotlinWebpackConfig.Mode.PRODUCTION
            }
        }
        binaries.library()
    }

    cocoapods {
        version = "1.0"
        ios.deploymentTarget = "13.5"
        pod("Bugsnag") {
            version = libs.versions.bugsnag.cocoa.get()
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.bugsnag.android)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(npm("@bugsnag/js", "=${libs.versions.bugsnag.js.get()}"))
            }
        }
    }
}

android {
    namespace = "com.bugsnag.kmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
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
