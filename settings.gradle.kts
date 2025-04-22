pluginManagement {
  includeBuild("build-logic")
  repositories {
    google()
    mavenCentral()
    mavenLocal()
    // https://oss.sonatype.org/content/repositories/snapshots/dev/zacsweers/metro/
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    gradlePluginPortal()
  }
}
plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
dependencyResolutionManagement {
  repositories {
    google()
    mavenLocal()
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
  }
}

rootProject.name = "mad-interop"
include(":app")
include(":scopes:public")
include(":use-cases:feature-with-graph:public")
include(":use-cases:feature-with-graph:impl")
include(":use-cases:contributed-module:public")
include(":use-cases:contributes-multibinding:public")
include(":use-cases:contributes-multibinding:impl")
include(":use-cases:dagger-generated-factory:public")
include(":use-cases:dagger-generated-factory:impl")
include(":use-cases:inject-replaced-contribution:public")
include(":use-cases:inject-replaced-contribution:impl")
include(":custom-generators")
