plugins {
  alias(libs.plugins.kotlin.jvm)
  id("mad.di")
}

dependencies {
  api(project(":use-cases:contributes-multibinding:public"))
  implementation(project(":scopes:public"))
}
