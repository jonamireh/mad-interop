package com.mad.interop.cycle

import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.optional.SingleIn
import javax.inject.Inject

@SingleIn(scope = LoggedInScope::class)
class Cycle1 @Inject constructor(
    cycle2: Cycle2
)
