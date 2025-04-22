package com.mad.multibinding.impl

import com.mad.interop.scopes.AppScope
import com.mad.interop.scopes.ContributesMultibindingScoped
import com.mad.interop.scopes.Scoped
import com.mad.multibinding.UseCaseSpecificInterface
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.optional.SingleIn
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesMultibindingScoped(scope = AppScope::class)
class ScopedUseCase @Inject constructor() : Scoped, UseCaseSpecificInterface

@ContributesTo(AppScope::class)
interface ScopedUseCaseAccessor {
  fun scopedUseCase(): UseCaseSpecificInterface
}
