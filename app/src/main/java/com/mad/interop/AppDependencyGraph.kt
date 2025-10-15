package com.mad.interop

import com.mad.cycle.OptionalCycleReferenceModule
import com.mad.interop.scopes.AppScope
import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeComponent
import com.squareup.anvil.annotations.optional.SingleIn

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class, modules = [OptionalCycleReferenceModule::class])
interface AppGraph

@SingleIn(LoggedInScope::class)
@ContributesSubcomponent(LoggedInScope::class, parentScope = AppScope::class)
interface LoggedInGraph {

  @ContributesSubcomponent.Factory
  interface Factory {
    fun create(): LoggedInGraph
  }

  val multibindings: Set<Multibinding>

  @ContributesTo(AppScope::class)
  interface ParentBindings {
    val loggedInGraphFactory: Factory
  }
}
