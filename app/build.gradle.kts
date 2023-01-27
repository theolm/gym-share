plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.hilt)
    id(Plugins.parcelize)
    id(Plugins.ksp) version Versions.kspVersion
}

android {
    namespace = ConfigData.applicationBundle
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = ConfigData.applicationBundle
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = ConfigData.jvmTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = ConfigData.kotlinCompilerExtensionVersion
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        jniLibs.useLegacyPackaging = true
    }


}

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }

        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

dependencies {
    implementation(project(":color"))
    implementation(project(":core"))

    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.activityCompose)
    implementation(Deps.lifecycleRuntimeCompose)
    implementation(Deps.viewModelCompose)
    implementation(Deps.accompanistNavigationAnimation)
    implementation(Deps.accompanistPermissions)
    implementation(Deps.accompanistWebview)
    implementation(Deps.accompanistSystemUiController)
    implementation(platform(Deps.composeBom))
    implementation(Deps.composeFoundation)
    implementation(Deps.composeMaterialIconsExtended)
    implementation(Deps.composeAnimationGraphics)
    implementation(Deps.composeUi)
    implementation(Deps.composeMaterial)
    implementation(Deps.composeMaterial3)
    implementation(Deps.composeWindowSizeClass)
    implementation(Deps.composeRuntime)
    implementation(Deps.composeLiveData)
    implementation(Deps.hiltAndroid)
    implementation(Deps.hiltNavCompose)
    kapt(Deps.hiltCompiler)

    implementation(Deps.moshi)
    kapt(Deps.moshiCodeGen)

    implementation(Deps.roomRuntime)
    implementation(Deps.roomKtx)
    kapt(Deps.roomCompiler)

    implementation(Deps.composeDestinationCore)
    ksp(Deps.composeDestinationKsp)

    implementation(Deps.composeUiToolingPreview)
    debugImplementation(Deps.composeUiTooling)

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
