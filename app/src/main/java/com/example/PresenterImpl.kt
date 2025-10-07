package com.example

import com.mad.interop.scopes.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

interface Dependency1
interface Dependency2
interface Dependency3

interface Presenter

@ContributesBinding(AppScope::class)
class PresenterImpl @Inject constructor(
  val dep1: Dependency1?,
  val dep2: Dependency2?,
  val dep3: Dependency3?,
): Presenter
