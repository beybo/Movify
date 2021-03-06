import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

val claveTmdb: String = gradleLocalProperties(rootDir).getProperty("claveTmdb")

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.movify"
        minSdk = 24
        targetSdk = 30
        versionCode = 104
        versionName = "1.0.4"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }

    }


    buildTypes {
        release {
            isMinifyEnabled = false
            applicationIdSuffix = ".pad"
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            buildConfigField("String", "claveTmdb", claveTmdb)
        }
        getByName("release") {
            buildConfigField("String", "claveTmdb", claveTmdb)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
        kotlinCompilerVersion = "1.4.32"
    }
    packagingOptions {
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/LICENSE")
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/license.txt")
        resources.excludes.add("META-INF/NOTICE")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("META-INF/notice.txt")
        resources.excludes.add("META-INF/ASL2.0")
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-alpha01")
    implementation("androidx.activity:activity-compose:1.3.0-alpha08")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha01")

    val room_version = "2.3.0"

    implementation ("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:$room_version")

    // optional - Test helpers
    testImplementation ("androidx.room:room-testing:$room_version")

    implementation(
        group="com.github.holgerbrandl",
        name = "themoviedbapi",
        version = "1.12"
    ){
        exclude("commons-logging","commons-logging")
    }

    implementation("com.google.accompanist:accompanist-coil:0.10.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.10.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
}

kapt {
    correctErrorTypes = true
}