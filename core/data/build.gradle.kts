plugins {
    id("musicbrainz.android.library")
    id("musicbrainz.android.hilt")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.nntsl.musicbrainz.core.data"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))

    implementation(libs.kotlinx.serialization.json)

    testImplementation(project(":core:testing"))
    testImplementation(kotlin("test"))
}