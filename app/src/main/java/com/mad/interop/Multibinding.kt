package com.mad.interop

import com.mad.cycle.CycleReference
import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.ContributesMultibinding
import java.util.Optional
import javax.inject.Inject

interface Multibinding

@ContributesMultibinding(LoggedInScope::class)
class Multibinding1 @Inject constructor(
  val optional: Optional<CycleReference>
) : Multibinding

@ContributesMultibinding(LoggedInScope::class)
class Multibinding2 @Inject constructor(
  val optional: Optional<CycleReference>
) : Multibinding
