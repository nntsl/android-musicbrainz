pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "musicbrainz"
include(":app")
include(":feature:artists")
include(":core:common")
include(":core:data")
include(":core:domain")
include(":core:network")
include(":core:designsystem")
include(":core:testing")
include(":ui-test-hilt")
include(":core:data-test")
