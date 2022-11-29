plugins {
    id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_KAPT)
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

    implementation(Dependency.Kotlin.COROUTINES_CORE)
    implementation(Dependency.Kotlin.COROUTINES_ANDROID)

    implementation(Dependency.Hilt.HILT_ANDROID)
    kapt(Dependency.Hilt.HILT_ANDROID_COMPILER)

    implementation(Dependency.Retrofit.RETROFIT)
    implementation(Dependency.Retrofit.RETROFIT_CONVERTER_GSON)

    testImplementation(Dependency.UnitTest.JUNIT)
    testImplementation(Dependency.UnitTest.MOCKITO)

    implementation(Dependency.KTor.KTOR_CLIENT)
    implementation(Dependency.KTor.KTOR_ENGINE_CIO)
    implementation(Dependency.KTor.KTOR_JACKSON)
    implementation(Dependency.KTor.KTOR_LOGGING)
    implementation(Dependency.KTor.KTOR_CONTENT_NEGOTIATION)
}
