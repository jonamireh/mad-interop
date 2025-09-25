plugins {
  alias(libs.plugins.kotlin.jvm)
  id("mad.di")
}

dependencies {
  api(project(":common:public"))
  implementation(project(":scopes:public"))
}
