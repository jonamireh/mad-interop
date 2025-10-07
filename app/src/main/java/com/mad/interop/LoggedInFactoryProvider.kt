package com.mad.interop

import com.mad.interop.scopes.AppScope
import com.squareup.anvil.annotations.ContributesTo

@ContributesTo(AppScope::class)
interface LoggedInFactoryProvider1 {
  fun userFactory(): LoggedInGraph.Factory
}

@ContributesTo(AppScope::class)
interface LoggedInFactoryProvider2 {
  val loggedInFactory: LoggedInGraph.Factory
}
