package com.mad.multibinding.impl

import com.mad.interop.scopes.AppScope
import com.mad.multibinding.Scoped
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.optional.ForScope
import com.squareup.anvil.annotations.optional.SingleIn
import javax.inject.Inject

@SingleIn(AppScope::class)
class AppScopeScopeRunner @Inject constructor(
  // Removing for now until use case is supported by Metro
  // @ForScope(AppScope::class)
  val scopedType: Set<@JvmSuppressWildcards Scoped>
)

@ContributesTo(AppScope::class)
interface AppScopeRunnerAccessor {
  fun appScopeRunner(): AppScopeScopeRunner
}