package com.mad.interop

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

    (appGraph as AppDependencyConsumerAccessor).appConsumer.doWork()
    (loggedInGraph as LoggedInDependencyConsumerAccessor).loggedInConsumer.doWork()
  }
}
