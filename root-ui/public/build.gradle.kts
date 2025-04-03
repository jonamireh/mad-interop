plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.anvil)
}

dependencies {
  implementation(libs.anvil.annotations)
  implementation(libs.anvil.annotations.optional)
  implementation(project(":scopes:public"))
  implementation(project(":feature-with-graph:public"))
}