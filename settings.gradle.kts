pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "SimTong-Android"
include (":app")
include(":common")
include(":core")
include(":core-design-system")
include(":feature-auth")
include(":domain")
include(":data")
include(":local")
include(":remote")
