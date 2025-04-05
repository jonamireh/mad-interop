package com.mad.interop.feature.graph.impl

import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.optional.SingleIn

annotation class ContributedFeatureScope

@SingleIn(ContributedFeatureScope::class)
@ContributesSubcomponent(
  scope = ContributedFeatureScope::class,
  parentScope = LoggedInScope::class,
)
interface ContributedFeatureGraph {
  fun contributedFeature(): FeaturePrinter
}
