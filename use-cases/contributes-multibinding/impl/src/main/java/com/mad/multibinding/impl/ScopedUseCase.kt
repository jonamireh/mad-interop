package com.mad.multibinding.impl

import com.mad.interop.scopes.AppScope
import com.mad.multibinding.Scoped
import com.mad.multibinding.UseCaseSpecificInterface
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.anvil.annotations.ContributesMultibinding
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.optional.ForScope
import com.squareup.anvil.annotations.optional.SingleIn
import javax.inject.Inject

// Disabling for now until use case is supported by Metro
// @ForScope(AppScope::class)
@SingleIn(AppScope::class)
@ContributesMultibinding(AppScope::class, boundType = Scoped::class)
@ContributesBinding(
  AppScope::class,
  boundType = UseCaseSpecificInterface::class,
  ignoreQualifier = true
)
class ScopedUseCase @Inject constructor() : Scoped, UseCaseSpecificInterface

@ContributesTo(AppScope::class)
interface ScopedUseCaseAccessor {
  fun scopedUseCase(): UseCaseSpecificInterface
}