import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("com.vanniktech.maven.publish") version "0.32.0"
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
}

version = "${project.properties["VERSION_NAME"]}"
group = "${project.properties["GROUP"]}"

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
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
        target.compilations {
            val main by getting {
                cinterops {
                    val bugsnag by creating {
                        definitionFile.set(file("src/appleMain/nativeInterop/cinterop/bugsnag.def"))
                        packageName("com.bugsnag.cocoa")
                        compilerOpts("-I/${rootProject.file("vendor/bugsnag-cocoa/Bugsnag/include")}")
                    }
                }
            }
        }

        target.binaries.framework {
            baseName = xcframeworkName
            binaryOption("bundleId", "com.bugsnag.cocoa.$xcframeworkName")
            isStatic = true

            xcf.add(this)
        }
    }

    js(IR) {
        useEsModules()
        browser {
            commonWebpackConfig {
                mode = KotlinWebpackConfig.Mode.PRODUCTION
            }

            testTask {
                useKarma {
                    useChromeHeadless()
                    useFirefox()
                }
            }
        }
        binaries.library()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val androidMain by getting {
            dependencies {
                api(libs.bugsnag.android)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(npm("@bugsnag/browser", "=${libs.versions.bugsnag.js.get()}"))
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

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    pom {
        name = "Bugsnag KMP"
        description = "Kotlin Multiplatform library for Bugsnag crash reporting"
        inceptionYear = "2025"
        url = "https://github.com/bugsnag/bugsnag-kmp"
        licenses {
            license {
                name = "The MIT License"
                url = "https://opensource.org/licenses/MIT"
                distribution = "repo"
            }
        }
        developers {
            developer {
                id = "bugsnag"
                name = "Bugsnag Team"
                email = "support@bugsnag.com"
            }
        }
        scm {
            url = "https://github.com/bugsnag/bugsnag-kotlin-multiplatform"
            connection = "scm:git:git://github.com/bugsnag/bugsnag-kotlin-multiplatform.git"
            developerConnection = "scm:git:ssh://git@github.com/bugsnag/bugsnag-kotlin-multiplatform.git"
        }
    }
}
