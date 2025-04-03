package com.mad.interop

import com.mad.interop.scopes.AppScope
import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.MergeComponent
import com.squareup.anvil.annotations.optional.SingleIn

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
interface AppGraph

@SingleIn(LoggedInScope::class)
@ContributesSubcomponent(LoggedInScope::class, parentScope = AppScope::class)
interface LoggedInGraph
