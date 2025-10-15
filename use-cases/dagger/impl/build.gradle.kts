plugins {
  alias(libs.plugins.kotlin.jvm)
}

dependencies {
  annotationProcessor(libs.dagger.compiler)
  implementation(libs.dagger)
  implementation(libs.anvil.annotations.optional)
  implementation(project(":scopes:public"))
}
