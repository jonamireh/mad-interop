plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.ksp)
}

dependencies {
  implementation(project(":scopes:public"))

  ksp(libs.auto.service.ksp)
  implementation(libs.auto.service.annotations)
  implementation(libs.kotlin.poet)
  implementation(libs.kotlin.poet.ksp)
  implementation(libs.ksp.api)
  implementation(libs.javax.inject)
  implementation(libs.dagger)
  implementation(libs.anvil.annotations)
  implementation(libs.anvil.annotations.optional)
}
