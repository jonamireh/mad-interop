package com.mad.interop.cycle

import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.optional.SingleIn
import javax.inject.Inject
import javax.inject.Provider

@SingleIn(scope = LoggedInScope::class)
class Cycle2 @Inject constructor(
  cycle1Provider: Provider<Cycle1>
)
