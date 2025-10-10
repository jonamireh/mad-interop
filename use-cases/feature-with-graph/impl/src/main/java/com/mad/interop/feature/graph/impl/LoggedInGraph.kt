package com.mad.interop.feature.graph.impl

import com.mad.interop.scopes.AppScope
import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.optional.SingleIn
import dagger.BindsInstance

@SingleIn(LoggedInScope::class)
@ContributesSubcomponent(LoggedInScope::class, parentScope = AppScope::class)
interface LoggedInGraph {

  @ContributesSubcomponent.Factory
  interface Factory {
    fun create(
      @BindsInstance name: String
    ): LoggedInGraph
  }

  @ContributesTo(AppScope::class)
  interface ParentBindings {
    fun factoryForLoggedInGraph(): Factory

    fun createLoggedInGraph(name: String): LoggedInGraph = factoryForLoggedInGraph().create(name)
  }
}
