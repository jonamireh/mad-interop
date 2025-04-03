plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.anvil)
}

dependencies {
  kapt(libs.dagger.compiler)
  implementation(libs.dagger)
  implementation(libs.anvil.annotations)
  implementation(libs.anvil.annotations.optional)
  implementation(project(":scopes:public"))
  implementation(project(":feature-with-graph:impl"))
  implementation(project(":root-ui:public"))

  testImplementation(libs.junit)
  testImplementation(libs.truth)
}