// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.metro) apply false
  alias(libs.plugins.anvil) apply false
  alias(libs.plugins.kotlin.kapt) apply false
  alias(libs.plugins.spotless)
}

// Spotless configuration for the entire project
spotless {
  kotlin {
    target("**/*.kt")
    targetExclude("**/build/**/*.kt", "**/generated/**/*.kt", "**/spotless/**/*.kt")
    ktlint("1.2.1").editorConfigOverride(
      mapOf(
        "indent_size" to "2",
        "continuation_indent_size" to "2",
        "ij_kotlin_code_style_defaults" to "KOTLIN_OFFICIAL",
        "ij_kotlin_indent_size" to "2",
        "ij_kotlin_continuation_indent_size" to "2",
        // Keep trailing commas on the same line in chained calls
        "ktlint_standard_trailing-comma-on-call-site" to "disabled",
        // Allow chained method calls to stay on the same line
        "ktlint_standard_chain-wrapping" to "disabled"
      ),
    )
    trimTrailingWhitespace()
    endWithNewline()
  }
  kotlinGradle {
    target("**/*.gradle.kts")
    ktlint("1.2.1").editorConfigOverride(
      mapOf(
        "indent_size" to "2",
        "continuation_indent_size" to "2",
        "ij_kotlin_code_style_defaults" to "KOTLIN_OFFICIAL",
        "ij_kotlin_indent_size" to "2",
        "ij_kotlin_continuation_indent_size" to "2",
        // Keep trailing commas on the same line in chained calls
        "ktlint_standard_trailing-comma-on-call-site" to "disabled",
        // Allow chained method calls to stay on the same line
        "ktlint_standard_chain-wrapping" to "disabled"
      ),
    )
    trimTrailingWhitespace()
    endWithNewline()
  }
}
