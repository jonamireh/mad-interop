plugins {
  alias(libs.plugins.kotlin.jvm)
  id("mad.di")
}

di {
  daggerOnly = true
}

dependencies {
  implementation(project(":scopes:public"))
}