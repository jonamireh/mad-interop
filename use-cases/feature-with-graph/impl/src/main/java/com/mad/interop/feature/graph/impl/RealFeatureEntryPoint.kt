package com.mad.interop.feature.graph.impl

import com.mad.feature.graph.FeatureEntryPoint
import com.mad.interop.scopes.GraphHolder
import com.mad.interop.scopes.LoggedInScope
import com.mad.interop.scopes.attemptDaggerChildComponentCreation
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.anvil.annotations.optional.SingleIn
import javax.inject.Inject

@ContributesBinding(LoggedInScope::class)
class RealFeatureEntryPoint @Inject constructor() : FeatureEntryPoint {
  override fun startFeature(): String {
    val contributedFeatureGraph = attemptDaggerChildComponentCreation<ContributedFeatureGraph>(
      GraphHolder.loggedInGraph!!,
    )!!
    return contributedFeatureGraph.contributedFeature().print()
  }
}

@SingleIn(ContributedFeatureScope::class)
class FeaturePrinter @Inject constructor() {
  // Text that is displayed by a higher scope, despite intermediate layers not directly knowing of
  // ContributedFeatureScope.
  fun print() = "Contributed Feature Text"
}
