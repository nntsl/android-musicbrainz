plugins {
    id("musicbrainz.android.feature")
    id("musicbrainz.android.hilt")
    id("musicbrainz.android.library.compose")
}

android {
    namespace = "com.nntsl.musicbrainz.feature.artists"
}

dependencies {
    implementation(libs.androidx.compose.material3)
    implementation(libs.accompanist.flowlayout)
}
