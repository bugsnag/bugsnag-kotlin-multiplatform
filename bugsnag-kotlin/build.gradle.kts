import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.native.cocoapods)
}

kotlin {
    explicitApi()
    androidTarget()

    val xcframeworkName = "BugsnagKMP"
    val xcf = XCFramework(xcframeworkName)

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { target ->
        target.binaries.framework {
            baseName = xcframeworkName
            binaryOption("bundleId", "com.bugsnag.cocoa.${xcframeworkName}")
            xcf.add(this)
            isStatic = true
        }
    }

    cocoapods {
        version = "1.0"
        ios.deploymentTarget = "13.5"
        pod("Bugsnag") {
            version = "6.28.1"
        }
    }
}

android {
    namespace = "com.bugsnag.kmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
