plugins {
  alias(libs.plugins.kotlin.jvm)
  id("mad.di")
}

dependencies {
  implementation(project(":scopes:public"))
  implementation(project(":feature-with-graph:impl"))
  implementation(project(":root-ui:public"))

  testImplementation(libs.junit)
  testImplementation(libs.truth)
}