plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.ksp)
  id("mad.di")
}

dependencies {
  api(project(":use-cases:contributes-multibinding:public"))
  implementation(project(":scopes:public"))
  ksp(project(":custom-generators"))
}
