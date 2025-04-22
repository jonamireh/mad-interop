package com.mad.interop.scopes

import kotlin.reflect.KClass

annotation class ContributesMultibindingScoped(
  val scope: KClass<*>,
  val boundType: KClass<*> = Unit::class,
)
