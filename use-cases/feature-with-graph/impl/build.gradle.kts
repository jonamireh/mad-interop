plugins {
  alias(libs.plugins.kotlin.jvm)
  id("mad.di")
}

dependencies {
  api(project(":scopes:public"))
  api(project(":use-cases:feature-with-graph:public"))
}
