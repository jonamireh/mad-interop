plugins {
  alias(libs.plugins.kotlin.jvm)
  id("mad.di")
}

dependencies {
  implementation(project(":scopes:public"))
  implementation(project(":use-cases:feature-with-graph:impl"))
  implementation(project(":use-cases:contributes-multibinding:impl"))
  implementation(project(":root-ui:public"))

  testImplementation(libs.junit)
  testImplementation(libs.truth)
}