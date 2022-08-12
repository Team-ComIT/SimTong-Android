plugins {
    id(BuildPlugins.KT_LINT) version Versions.KT_LINT
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependency.GradlePlugin.GRADLE_ANDROID)
        classpath(Dependency.GradlePlugin.GRADLE_KOTLIN)
        classpath(Dependency.GradlePlugin.GRADLE_HILT)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
