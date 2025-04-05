package com.mad.feature.graph

import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.ContributesTo

interface FeatureEntryPoint {
  fun startFeature(): String
}

@ContributesTo(LoggedInScope::class)
interface FeatureEntryPointProvider {
  fun featureEntryPoint(): FeatureEntryPoint
}
