package com.mad.interop.root.ui

import com.mad.feature.graph.FeatureEntryPointProvider
import com.mad.interop.scopes.GraphHolder

object FeatureLoader {
  fun loadFirstFeature(): String {
    return GraphHolder.loggedInGraph<FeatureEntryPointProvider>()
      .featureEntryPoint()
      .startFeature()
  }
}