plugins {
  alias(libs.plugins.kotlin.jvm)
  id("mad.di")
}

dependencies {
  implementation(project(":use-cases:feature-with-graph:impl"))
  implementation(project(":use-cases:contributes-multibinding:impl"))
  implementation(project(":use-cases:dagger-generated-factory:impl"))

  testImplementation(libs.junit)
  testImplementation(libs.truth)
}
