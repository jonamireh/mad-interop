pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
  }
}

dependencyResolutionManagement {
  repositories {
    mavenLocal()
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
  }
  versionCatalogs {
    create("libs") {
      from(files("../gradle/libs.versions.toml"))
    }
  }
}

rootProject.name = "build-logic"
include(":convention")
