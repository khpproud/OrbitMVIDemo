object Versions {
    const val gradleAndroidPlugin = "7.1.2"
    const val detektPlugin = "1.17.1"
    const val navSafeArgs = "2.3.5"
    const val atomicfu = "0.16.2"

    const val kotlin = "1.5.30-M1"
    const val coroutines = "1.5.1-native-mt"

    const val lifecycle = "2.4.0-alpha03"
    const val espresso = "3.4.0"

    const val desuagr = "1.1.5"

    // Testing
    const val jacoco = "0.8.7"
    const val junit4 = "4.13.2"
    const val robolectric = "4.6.1"
}

object PluginDependencies {
    const val android = "com.android.tools.build:gradle:${Versions.gradleAndroidPlugin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detektPlugin}"
    const val safeargs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navSafeArgs}"
    const val atomicfu = "org.jetbrains.kotlinx:atomicfu-gradle-plugin:${Versions.atomicfu}"
}

object ProjectDependencies {
    // Kotlin
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"

    // AndroidX
    const val lifecycleSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
    const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val espressoIdlingResource = "androidx.text.espresso:espresso-idling-resource:${Versions.espresso}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    // Tools
    const val detektFormatting = "io.gitlab.arturbosch:detekt:detekt-formatting:${Versions.detektPlugin}"

    // Test
    const val junit4 = "junit:junit:${Versions.junit4}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
}