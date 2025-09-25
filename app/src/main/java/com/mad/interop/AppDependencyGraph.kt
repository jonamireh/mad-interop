package com.mad.interop

import com.mad.dagger.factory.AppDependencyConsumer
import com.mad.dagger.factory.LoggedInDependencyConsumer
import com.mad.interop.scopes.AppScope
import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeComponent
import com.squareup.anvil.annotations.optional.SingleIn
import dev.zacsweers.metro.GraphExtension

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
interface AppGraph

@SingleIn(LoggedInScope::class)
@ContributesSubcomponent(LoggedInScope::class, parentScope = AppScope::class)
@GraphExtension(LoggedInScope::class)
interface LoggedInGraph {

  @ContributesSubcomponent.Factory
  @GraphExtension.Factory
  interface Factory {
    fun create(): LoggedInGraph
  }
}

@ContributesTo(AppScope::class)
interface AppDependencyConsumerAccessor {
  val appConsumer: AppDependencyConsumer
}

@ContributesTo(LoggedInScope::class)
interface LoggedInDependencyConsumerAccessor {
  val loggedInConsumer: LoggedInDependencyConsumer
}
