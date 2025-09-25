package com.mad.interop.feature.graph.impl

import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.optional.SingleIn
import dev.zacsweers.metro.ContributesGraphExtension
import dev.zacsweers.metro.GraphExtension

annotation class ContributedFeatureScope

@SingleIn(ContributedFeatureScope::class)
@ContributesSubcomponent(
  scope = ContributedFeatureScope::class,
  parentScope = LoggedInScope::class,
)
@GraphExtension(scope = ContributedFeatureScope::class)
interface ContributedFeatureGraph {
  fun contributedFeature(): FeaturePrinter

  @ContributesSubcomponent.Factory
  @GraphExtension.Factory
  interface Factory {
    fun create(): ContributedFeatureGraph
  }
}
