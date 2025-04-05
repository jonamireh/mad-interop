plugins {
  alias(libs.plugins.kotlin.jvm)
  id("mad.di")
}

dependencies {
  api(project(":use-cases:dagger-generated-factory:public"))
  implementation(project(":scopes:public"))
}
