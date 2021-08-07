// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(PluginDependencies.android)
        classpath(PluginDependencies.kotlin)
        classpath(PluginDependencies.safeargs)
        classpath(PluginDependencies.atomicfu)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

apply(from = "gradle/scripts/detekt.gradle.kts")

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }

    plugins.withId("com.android.application") {
        configure<com.android.build.gradle.AppExtension> {
            sourceSets {
                get("main").java.srcDir("src/main/kotlin")
                get("test").java.srcDir("src/test/kotlin")
            }
        }
    }
}