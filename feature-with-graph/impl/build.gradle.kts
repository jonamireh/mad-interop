plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.anvil)
}

dependencies {
  kapt(libs.dagger.compiler)
  api(libs.dagger)
  api(libs.anvil.annotations)
  api(libs.anvil.annotations.optional)
  api(project(":scopes:public"))
  api(project(":feature-with-graph:public"))
}