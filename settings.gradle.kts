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

rootProject.name = "SampleGame"
include(":app")
include(":core-designsystem")
include(":feature-gacha")
include(":feature-settings")
include(":core-data")
include(":core-network")
include(":core-model")
include(":core-datastore")
include(":core-common")
include(":core-datastore-proto")
