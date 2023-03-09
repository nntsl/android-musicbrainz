plugins {
    id("musicbrainz.android.library")
    id("org.jetbrains.kotlin.android")
    id("musicbrainz.android.hilt")
}

android {
    namespace = "com.nntsl.musicbrainz.core.data.test"
}

dependencies {
    api(project(":core:data"))
    api(project(":core:domain"))
    implementation(project(":core:testing"))
}
