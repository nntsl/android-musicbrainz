plugins {
    id("musicbrainz.android.library")
    id("musicbrainz.android.hilt")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.nntsl.musicbrainz.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(project(":core:testing"))
    testImplementation(kotlin("test"))
    testImplementation(libs.turbine)
}
