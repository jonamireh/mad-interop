plugins {
  alias(libs.plugins.kotlin.jvm)
}

dependencies {
  api(libs.javax.inject)
  api(libs.kotlin.reflect)
  api(libs.metro.runtime)
}