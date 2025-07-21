package com.mad.interop

import com.mad.interop.scopes.AppScope
import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.MergeComponent
import com.squareup.anvil.annotations.optional.SingleIn
import dev.zacsweers.metro.ContributesGraphExtension

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
interface AppGraph

@SingleIn(LoggedInScope::class)
@ContributesSubcomponent(LoggedInScope::class, parentScope = AppScope::class)
@ContributesGraphExtension(LoggedInScope::class, isExtendable = true)
interface LoggedInGraph {

  @ContributesSubcomponent.Factory
  @ContributesGraphExtension.Factory(AppScope::class)
  interface Factory {
    fun create(): LoggedInGraph
  }
}
