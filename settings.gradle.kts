pluginManagement {
  includeBuild("build-logic")
  repositories {
    google()
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
    maven(url = "https://central.sonatype.com/repository/maven-snapshots/")
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
    maven(url = "https://central.sonatype.com/repository/maven-snapshots/")
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
include(":use-cases:dagger-module-inclusion")
include(":use-cases:inject-replaced-contribution:public")
include(":use-cases:inject-replaced-contribution:impl")
include(":custom-generators")
