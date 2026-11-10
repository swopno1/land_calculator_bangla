import java.util.Properties as JProperties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.compose)
}

android {
  namespace = "com.vivescriptsolutions.jomirhisab"
  compileSdk { version = release(37) }

  defaultConfig {
    applicationId = "com.vivescriptsolutions.jomirhisab"
    minSdk = 24
    targetSdk = 36
    versionCode = 6
    versionName = "6.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  signingConfigs {
    create("release") {
      val keystorePath = System.getenv("KEYSTORE_PATH") ?: "${rootDir}/my-upload-key.jks"
      val keyFile = file(keystorePath)
      if (keyFile.exists()) {
        storeFile = keyFile
        storePassword = System.getenv("STORE_PASSWORD") ?: "vivescript2026"
        keyAlias = System.getenv("KEY_ALIAS") ?: "upload"
        keyPassword = System.getenv("KEY_PASSWORD") ?: "vivescript2026"
      } else {
        storeFile = file("${rootDir}/debug.keystore")
        storePassword = "android"
        keyAlias = "androiddebugkey"
        keyPassword = "android"
      }
    }
    create("debugConfig") {
      storeFile = file("${rootDir}/debug.keystore")
      storePassword = "android"
      keyAlias = "androiddebugkey"
      keyPassword = "android"
    }
  }

  buildTypes {
    release {
      isCrunchPngs = false
      isMinifyEnabled = true
      isShrinkResources = true
      ndk {
        debugSymbolLevel = "SYMBOL_TABLE"
      }
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      signingConfig = signingConfigs.getByName("release")
      // Live Google AdMob ad units (release only).
      buildConfigField("String", "ADMOB_BANNER_AD_UNIT_ID", "\"ca-app-pub-5222053984568989/8436765853\"")
      buildConfigField("String", "ADMOB_INTERSTITIAL_AD_UNIT_ID", "\"ca-app-pub-5222053984568989/8246948185\"")
    }
    debug {
      signingConfig = signingConfigs.getByName("debugConfig")
      // Official Google demo ad units: testing can never taint live ad traffic
      // or trigger invalid-activity enforcement on the production units.
      buildConfigField("String", "ADMOB_BANNER_AD_UNIT_ID", "\"ca-app-pub-3940256099942544/6300978111\"")
      buildConfigField("String", "ADMOB_INTERSTITIAL_AD_UNIT_ID", "\"ca-app-pub-3940256099942544/1033173712\"")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
  lint {
    checkReleaseBuilds = false
    abortOnError = false
  }
  testOptions { unitTests { isIncludeAndroidResources = true } }
  dependenciesInfo {
    includeInApk = false
    includeInBundle = true
  }
}

configurations.all {
  resolutionStrategy {
    force("androidx.fragment:fragment:1.8.6")
    force("androidx.fragment:fragment-ktx:1.8.6")
  }
}

dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.compose.material.icons.core)
  implementation(libs.androidx.compose.material.icons.extended)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.graphics)
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.play.services.ads)
  implementation(libs.play.services.ump)
  testImplementation(libs.junit)
  debugImplementation(libs.androidx.compose.ui.tooling)
}
