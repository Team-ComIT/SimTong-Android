plugins {
    id(BuildPlugins.ANDROID_APPLICATION_PLUGIN)
    id(BuildPlugins.DAGGER_HILT_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.FIREBASE_DISTRIBUTION)
    id(BuildPlugins.GOOGLE_SERVICE)
    id(BuildPlugins.FIREBASE_CRASHLYTICS)
}
android {
    compileSdk = ProjectProperties.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = ProjectProperties.APPLICATION_ID
        minSdk = ProjectProperties.MIN_SDK_VERSION
        targetSdk = ProjectProperties.TARGET_SDK_VERSION
        versionCode = ProjectProperties.VERSION_CODE
        versionName = ProjectProperties.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        dataBinding = true
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

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
        kotlinCompilerVersion = ProjectProperties.KOTLIN_VERSION
    }

    kotlinOptions {
        jvmTarget = ProjectProperties.JAVA_VERSION.toString()
    }
}

dependencies {
    implementation(projects.navigator)
    implementation(projects.local)
    implementation(projects.remote)
    implementation(projects.data)
    implementation(projects.domain)
    implementation(projects.di)
    implementation(projects.feature.featureOnboarding)
    implementation(projects.feature.featureMypage)
    implementation(projects.feature.featureHome)
    implementation(projects.coreDesignSystem)
    implementation(projects.commonCompose)
    implementation(projects.commonUtil)

    implementation(Dependency.Logger.TIMBER)

    implementation(Dependency.Hilt.HILT_ANDROID)
    kapt(Dependency.Hilt.HILT_ANDROID_COMPILER)
    implementation(Dependency.Compose.COMPOSE_HILT_NAV)

    implementation(platform(Dependency.FIREBASE.FIREBASE_BOM))
    implementation(Dependency.FIREBASE.FIREBASE_MESSAGING)
    implementation(Dependency.FIREBASE.FIREBASE_CRASHLYTICS)
    implementation(Dependency.FIREBASE.FIREBASE_ANALYTICS)

    implementation(Dependency.Kotlin.COROUTINES_CORE)
    implementation(Dependency.Kotlin.COROUTINES_ANDROID)
    implementation(Dependency.Kotlin.KOTLIN_STDLIB)

    implementation(Dependency.Moshi.MOSHI)
    kapt(Dependency.Moshi.MOSHI_COMPILER)

    implementation(Dependency.Compose.COMPOSE_ACTIVITY)
    implementation(Dependency.Compose.COMPOSE_MATERIAL)
    implementation(Dependency.Compose.COMPOSE_PREVIEW)
    implementation(Dependency.Compose.COMPOSE_UI)
    implementation(Dependency.Compose.COMPOSE_NAV)
    implementation(Dependency.Compose.COMPOSE_ANI_NAV)
    implementation(Dependency.Compose.COMPOSE_LANDSCAPIST)

    implementation(Dependency.Retrofit.RETROFIT)
    implementation(Dependency.Retrofit.RETROFIT_CONVERTER_GSON)

    implementation(Dependency.OkHttp.OKHTTP)
    implementation(Dependency.OkHttp.OKHTTP_LOGGING_INTERCEPTOR)

    implementation(Dependency.Ui.SPLASH_SCREEN_API)
    implementation(Dependency.Ui.CORE_KTX)
    implementation(Dependency.Ui.APP_COMPAT)
    implementation(Dependency.Ui.MATERIAL)
    implementation(Dependency.Ui.CONSTRAINT_LAYOUT)
}
