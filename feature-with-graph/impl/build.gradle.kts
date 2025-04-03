plugins {
  alias(libs.plugins.kotlin.jvm)
  id("mad.di")
}

dependencies {
  api(project(":scopes:public"))
  api(project(":feature-with-graph:public"))
}