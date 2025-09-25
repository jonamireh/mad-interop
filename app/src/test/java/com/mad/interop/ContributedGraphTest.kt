package com.mad.interop

import com.google.common.truth.Truth.assertThat
import com.mad.interop.scopes.attemptDaggerChildComponentCreation
import com.mad.interop.scopes.createGraphInterop
import org.junit.Test

class ContributedGraphTest {

  @Test
  fun `qualified dependency can be resolved from java`() {
    val appGraph = createGraphInterop<AppGraph>()
    val loggedInGraph = attemptDaggerChildComponentCreation<LoggedInGraph.Factory>(
      appGraph,
    )!!.create()

    assertThat((appGraph as AppDependencyConsumerAccessor).appConsumer.doWork()).isEqualTo("AppScope")
    assertThat((loggedInGraph as LoggedInDependencyConsumerAccessor).loggedInConsumer.doWork()).isEqualTo("LoggedInScope")
  }
}
