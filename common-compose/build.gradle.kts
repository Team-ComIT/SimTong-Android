plugins {
    id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
}

android {
    namespace = "com.commit.common"
    compileSdk = ProjectProperties.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = ProjectProperties.MIN_SDK_VERSION
        targetSdk = ProjectProperties.TARGET_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = ProjectProperties.JAVA_VERSION
        targetCompatibility = ProjectProperties.JAVA_VERSION
    }

    kotlinOptions {
        jvmTarget = ProjectProperties.JAVA_VERSION.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
        kotlinCompilerVersion = ProjectProperties.KOTLIN_VERSION
    }
}

dependencies {
    implementation(projects.coreDesignSystem)

    implementation(Dependency.Compose.COMPOSE_ACTIVITY)
    implementation(Dependency.Compose.COMPOSE_MATERIAL)
    implementation(Dependency.Compose.COMPOSE_UI)
    implementation(Dependency.Compose.COMPOSE_UI_TOOL)
}
