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

val modules = hashMapOf<String, String>()

rootProject.projectDir.listFiles()
    ?.forEach {
        findSubProjects(it)
    }

fun findSubProjects(file: File) {
    if (file.name.startsWith(".")) {
        return
    }

    if (file.name == "build.gradle.kts" || file.name == "build.gradle") {
        modules[file.parentFile.name] = file.parentFile.path
        return
    }

    if (file.isDirectory) {
        file.listFiles()
            ?.forEach {
                findSubProjects(it)
            }
    }
}

for (project in rootProject.children) {
    if (modules.containsKey(project.name)) {
        val directory = modules[project.name] ?: continue
        project.projectDir = File(directory)
    }
}

rootProject.name = "SimTong-Android"
include(":app")
include(":feature")
include(":common")
include(":core")
include(":core-design-system")
include(":feature:feature-mypage")
include(":feature:feature-auth")
include(":domain")
include(":data")
include(":local")
include(":remote")
include(":navigator")
include(":di")
