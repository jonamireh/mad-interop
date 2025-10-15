plugins {
  alias(libs.plugins.kotlin.jvm)
  id("mad.di")
}

dependencies {
  implementation(project(":use-cases:dagger:impl"))
  implementation(project(":scopes:public"))

  testImplementation(libs.junit)
  testImplementation(libs.truth)
}
