package com.mad.interop

import com.mad.interop.scopes.AppScope
import com.mad.interop.scopes.LoggedInScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.Named

interface Dependency

@ContributesBinding(AppScope::class)
@Named("AppQualifier")
class AppDependency @Inject constructor(): Dependency

@ContributesBinding(LoggedInScope::class)
@Named("LoggedInQualifier")
class LoggedInDependency @Inject constructor() : Dependency

interface Base {
  fun dependency(): Dependency
}

@ContributesTo(AppScope::class)
interface AppBase : Base {
  @Named("AppQualifier")
  override fun dependency(): Dependency
}


@ContributesTo(LoggedInScope::class)
interface UserBase : Base {
  @Named("LoggedInQualifier")
  override fun dependency(): Dependency
}
