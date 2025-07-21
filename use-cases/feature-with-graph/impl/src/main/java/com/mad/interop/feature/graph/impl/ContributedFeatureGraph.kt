package com.mad.interop.feature.graph.impl

import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.optional.SingleIn
import dev.zacsweers.metro.ContributesGraphExtension

annotation class ContributedFeatureScope

@SingleIn(ContributedFeatureScope::class)
@ContributesSubcomponent(
  scope = ContributedFeatureScope::class,
  parentScope = LoggedInScope::class,
)
@ContributesGraphExtension(scope = ContributedFeatureScope::class)
interface ContributedFeatureGraph {
  fun contributedFeature(): FeaturePrinter

  @ContributesSubcomponent.Factory
  @ContributesGraphExtension.Factory(LoggedInScope::class)
  interface Factory {
    fun create(): ContributedFeatureGraph
  }
}
