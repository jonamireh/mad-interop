package com.mad.interop

import com.mad.cycle.CycleReference
import com.mad.interop.cycle.Cycle2
import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.anvil.annotations.optional.SingleIn
import javax.inject.Inject

@SingleIn(scope = LoggedInScope::class)
@ContributesBinding(LoggedInScope::class)
class LoggedInCycleReference @Inject constructor(
  cycle2: Cycle2
) : CycleReference
