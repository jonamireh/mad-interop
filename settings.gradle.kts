pluginManagement {
  includeBuild("build-logic")
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    mavenLocal()
    //https://oss.sonatype.org/content/repositories/snapshots/dev/zacsweers/metro/
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
include(":feature-with-graph:public")
include(":feature-with-graph:impl")
include(":root-ui:public")