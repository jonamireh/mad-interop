package com.mad.interop

import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.optional.SingleIn
import dev.zacsweers.metro.Inject

@Inject
@SingleIn(LoggedInScope::class)
class MultibindingsReference(
  private val multibindings: Set<Multibinding>
)
