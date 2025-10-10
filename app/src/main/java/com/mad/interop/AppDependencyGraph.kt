package com.mad.interop

import com.mad.direct.module.IncludedModuleProvidedType
import com.mad.direct.module.IncludedObjectModule
import com.mad.interop.scopes.AppScope
import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeComponent
import com.squareup.anvil.annotations.optional.SingleIn
import dagger.BindsInstance
import dev.zacsweers.metro.GraphExtension

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class, modules = [IncludedObjectModule::class])
interface AppGraph {
  fun includedModuleTypeAccessor(): IncludedModuleProvidedType
}
