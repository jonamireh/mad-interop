package com.mad.interop

import com.google.common.truth.Truth.assertThat
import com.mad.interop.root.ui.FeatureLoader
import com.mad.interop.scopes.GraphHolder
import com.mad.interop.scopes.attemptDaggerChildComponentCreation
import com.mad.interop.scopes.attemptDaggerComponentCreation
import org.junit.Test

class ContributedGraphTest {
  @Test
  fun `contributed graph can be created via public api`() {
    GraphHolder.appGraph = attemptDaggerComponentCreation<AppGraph>()
    GraphHolder.loggedInGraph = attemptDaggerChildComponentCreation<LoggedInGraph>(
      GraphHolder.appGraph!!
    )

    // Referencing FeatureLoader in the root-ui/public to create ContributedFeatureGraph
    assertThat(FeatureLoader.loadFirstFeature())
      .isEqualTo("Contributed Feature Text")
  }
}