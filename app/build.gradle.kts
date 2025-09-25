plugins {
  alias(libs.plugins.kotlin.jvm)
  id("mad.di")
}

dependencies {
  implementation(project(":use-cases:dagger-generated-factory:impl"))
  implementation(project(":scopes:public"))
  implementation(project(":common:impl"))

  testImplementation(libs.junit)
  testImplementation(libs.truth)
}
