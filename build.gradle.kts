buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jfrog.anythinktech.com/artifactory/overseas_sdk")
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:9.1.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.21")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.9.6")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.57.2")
        classpath("com.jaredsburrows:gradle-license-plugin:0.9.8")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven {
            url = uri("https://jfrog.anythinktech.com/artifactory/overseas_sdk")
        }
    }
}

apply("./gradle/updateVersion.gradle.kts")
apply("./gradle/updateTranslations.gradle.kts")