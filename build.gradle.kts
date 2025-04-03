// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.kotlin.compose) apply false
  alias(libs.plugins.metro) apply false
  alias(libs.plugins.anvil) apply false
  alias(libs.plugins.kotlin.kapt) apply false
}