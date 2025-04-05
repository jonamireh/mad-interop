plugins {
  alias(libs.plugins.kotlin.jvm)
  id("mad.di")
}

dependencies {
  api(project(":use-cases:inject-replaced-contribution:public"))
  implementation(project(":scopes:public"))
}
