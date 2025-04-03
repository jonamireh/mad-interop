import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  `kotlin-dsl`
  `java-gradle-plugin`
}

group = "com.mad.buildlogic"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
  compilerOptions {
    jvmTarget = JvmTarget.JVM_17
  }
}

dependencies {
  compileOnly(libs.kotlin.gradle.plugin)
  implementation(libs.metro.gradle.plugin)
  implementation(gradleApi())
}

tasks {
  validatePlugins {
    enableStricterValidation = true
    failOnWarning = true
  }
}

gradlePlugin {
  plugins {
    register("di") {
      id = "mad.di"
      implementationClass = "com.mad.buildlogic.DiConventionPlugin"
    }
  }
}