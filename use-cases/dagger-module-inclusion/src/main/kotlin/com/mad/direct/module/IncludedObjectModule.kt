package com.mad.direct.module

import com.mad.interop.scopes.AppScope
import com.squareup.anvil.annotations.optional.SingleIn
import dagger.Module
import dagger.Provides

class IncludedModuleProvidedType()

@Module
object IncludedObjectModule {
  @Provides
  @SingleIn(AppScope::class)
  fun provideIncludedModuleType(): IncludedModuleProvidedType = IncludedModuleProvidedType()
}
