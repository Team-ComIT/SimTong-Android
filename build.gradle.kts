buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath (Dependency.GradlePlugin.GRADLE_ANDROID)
        classpath (Dependency.GradlePlugin.GRADLE_KOTLIN)
        classpath (Dependency.GradlePlugin.GRADLE_HILT)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}