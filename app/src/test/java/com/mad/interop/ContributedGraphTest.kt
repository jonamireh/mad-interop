package com.mad.interop

import com.google.common.truth.Truth.assertThat
import com.mad.interop.scopes.createGraphInterop
import dev.zacsweers.metro.asContribution
import dev.zacsweers.metro.createGraph
import org.junit.Test

class ContributedGraphTest {
  @Test
  fun `app graph can be built`() {
    val appGraph = createGraphInterop<AppGraph>()
    assertThat(appGraph).isInstanceOf(AppGraph::class.java)
  }

  @Test
  fun `cycle can be accessed`() {
    val appGraph = createGraph<AppGraph>()
    appGraph.asContribution<LoggedInGraph.ParentBindings>().loggedInGraphFactory.create()
  }
}
