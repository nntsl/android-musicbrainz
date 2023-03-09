plugins {
    id("musicbrainz.android.library")
    id("org.jetbrains.kotlin.android")
    id("musicbrainz.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.nntsl.musicbrainz.core.network"

    defaultConfig {
        buildConfigField("String","VERSION_CODE","\"${properties["VERSION_CODE"] as String}\"")
        buildConfigField("String","APP_NAME","\"${properties["APP_NAME"] as String}\"")
        buildConfigField("String","BACKEND_URL","\"${properties["BACKEND_URL"] as String}\"")
        buildConfigField("String","CONTACT_URL","\"${properties["CONTACT_URL"] as String}\"")
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)

    testImplementation(project(":core:testing"))
    testImplementation(kotlin("test"))
}
