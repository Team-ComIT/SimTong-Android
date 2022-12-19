plugins {
    id(BuildPlugins.KT_LINT) version Versions.KT_LINT
    id("io.gitlab.arturbosch.detekt") version "1.21.0"
    id("com.android.library") version "7.3.0" apply false
    id("org.jetbrains.kotlin.android") version ProjectProperties.KOTLIN_VERSION apply false
}

dependencies {
    detekt("io.gitlab.arturbosch.detekt:detekt-formatting:1.21.0")
}

allprojects {

    apply {
        plugin("org.jlleitschuh.gradle.ktlint")
        plugin("io.gitlab.arturbosch.detekt")
    }

    afterEvaluate {
        detekt {
            autoCorrect = true
            buildUponDefaultConfig = true
            config.setFrom(files("$rootDir/detekt-config.yml"))
        }
    }
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
        classpath(Dependency.GradlePlugin.GOOGLE_SERVICE)
        classpath(Dependency.GradlePlugin.FIREBASE_DISTRIBUTION)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
