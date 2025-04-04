package com.mad.buildlogic

import dev.zacsweers.metro.gradle.MetroPluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class DiConventionPlugin : Plugin<Project> {
  override fun apply(target: Project): Unit = target.run {
    val diExtension = extensions.create("di", DiConventionExtension::class.java)

    val diImplementation = if (diExtension.daggerOnly) {
      "DaggerOnly"
    } else {
      findProperty("mad.di") as? String ?: "AnvilDagger"
    }
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    // Allow convention plugin to override diImplementation
    when (diImplementation) {
      "AnvilDagger" -> {
        plugins.apply(libs.findPlugin("kotlin-kapt").get().get().pluginId)
        plugins.apply(libs.findPlugin("anvil").get().get().pluginId)

        dependencies {
          "implementation"(libs.findLibrary("dagger").get())
          "implementation"(libs.findLibrary("anvil-annotations").get())
          "implementation"(libs.findLibrary("anvil-annotations-optional").get())
          "kapt"(libs.findLibrary("dagger-compiler").get())
        }
      }

      "Metro" -> {
        plugins.apply(libs.findPlugin("metro").get().get().pluginId)
        dependencies {
          "implementation"(libs.findLibrary("metro-runtime").get())

          // Anvil + Dagger Annotations are still required for interop to work
          "implementation"(libs.findLibrary("dagger").get())
          "implementation"(libs.findLibrary("anvil-annotations").get())
          "implementation"(libs.findLibrary("anvil-annotations-optional").get())
        }
        val metro = extensions.getByType<MetroPluginExtension>()
        metro.interop.enableDaggerRuntimeInterop.set(true)
        metro.interop.includeJavax()
        metro.interop.includeDagger()
        metro.interop.includeAnvil(includeDaggerAnvil = true, includeKotlinInjectAnvil = false)
      }
      // This configuration is only intended for use by modules testing dagger interop features.
      "DaggerOnly" -> {
        plugins.apply(libs.findPlugin("kotlin-kapt").get().get().pluginId)

        dependencies {
          "implementation"(libs.findLibrary("dagger").get())
          "kapt"(libs.findLibrary("dagger-compiler").get())
        }
      }
      else -> error("Unsupported value for property 'mad.di': $diImplementation")
    }
  }
}

open class DiConventionExtension {
  var daggerOnly: Boolean = false
}