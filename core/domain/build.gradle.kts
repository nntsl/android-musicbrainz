plugins {
    id("musicbrainz.android.library")
    id("org.jetbrains.kotlin.android")
    id("musicbrainz.android.hilt")
}

android {
    namespace = "com.nntsl.musicbrainz.core.domain"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.kotlinx.serialization.json)

    testImplementation(project(":core:testing"))
    testImplementation(kotlin("test"))
}
