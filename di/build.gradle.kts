plugins {
    id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.DAGGER_HILT_PLUGIN)
}

android {
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
}

dependencies {
    implementation(projects.data)
    implementation(projects.domain)
    implementation(projects.remote)
    implementation(projects.local)

    implementation(Dependency.Hilt.HILT_ANDROID)
    kapt(Dependency.Hilt.HILT_ANDROID_COMPILER)

    implementation(Dependency.Room.ROOM_KTX)
    kapt(Dependency.Room.ROOM_COMPILER)

    implementation(Dependency.Retrofit.RETROFIT)
    implementation(Dependency.Retrofit.RETROFIT_CONVERTER_GSON)

    implementation(Dependency.OkHttp.OKHTTP)
    implementation(Dependency.OkHttp.OKHTTP_LOGGING_INTERCEPTOR)

    implementation(Dependency.Moshi.MOSHI)
    kapt(Dependency.Moshi.MOSHI_COMPILER)

    implementation(Dependency.KTor.KTOR_CLIENT)
    implementation(Dependency.KTor.KTOR_ENGINE_CIO)
    implementation(Dependency.KTor.KTOR_JACKSON)
    implementation(Dependency.KTor.KTOR_LOGGING)
    implementation(Dependency.KTor.KTOR_CONTENT_NEGOTIATION)
}
