import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin

buildscript {
    repositories {
        maven(url = "https://plugins.gradle.org/m2/")
        mavenCentral()
    }
    dependencies {
        classpath(PluginDependencies.detekt)
    }
}

repositories {
    mavenCentral()
    maven(url = "https://plugins.gradle.org/m2/")
}

apply<DetektPlugin>()

tasks.named("detekt", Detekt::class.java).configure {
    setSource(files(rootProject.projectDir))

    include("**/*.kt")
    include("**/*.kts")
    include("**/resources/**")
    include("**/build/**")

    parallel = true

    autoCorrect = true
    buildUponDefaultConfig = true
    config.setFrom(files("${rootProject.projectDir}/gradle/scripts/detekt.yml"))

    reports {
        xml {
            enabled = true
            destination = file("build/reports/detekt/detekt.xml")
        }
        html {
            enabled = true
        }
    }
}

dependencies {
    "detektPlugins"(ProjectDependencies.detektFormatting)
}