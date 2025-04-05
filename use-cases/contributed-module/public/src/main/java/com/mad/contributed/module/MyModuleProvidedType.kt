package com.mad.contributed.module

import com.mad.interop.scopes.AppScope
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.optional.SingleIn
import dagger.Binds
import dagger.Module
import dagger.Provides

interface MyModuleProvidedType

class RealMyModuleProvidedType : MyModuleProvidedType

@ContributesTo(AppScope::class)
@Module
interface MyModuleProvidedTypeModule {
  @Binds
  fun bindMyModuleProvidedType(real: RealMyModuleProvidedType): MyModuleProvidedType

  companion object {
    @Provides
    @SingleIn(AppScope::class)
    fun provideMyModuleProvidedType(): RealMyModuleProvidedType = RealMyModuleProvidedType()
  }
}

@ContributesTo(AppScope::class)
interface MyModuleProvidedTypeAccessor {
  fun myModuleProvidedType(): MyModuleProvidedType
  fun realMyModuleProvidedType(): RealMyModuleProvidedType
}
