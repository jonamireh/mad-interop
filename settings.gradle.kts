pluginManagement {
  includeBuild("build-logic")
  repositories {
    google()
    mavenCentral()
    maven("https://central.sonatype.com/repository/maven-snapshots")
    gradlePluginPortal()
  }
}
plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    maven("https://central.sonatype.com/repository/maven-snapshots")
  }
}

rootProject.name = "mad-interop"
include(":app")
include(":scopes:public")
include(":use-cases:dagger:impl")
