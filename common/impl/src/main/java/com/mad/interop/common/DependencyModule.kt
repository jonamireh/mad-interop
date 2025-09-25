package com.mad.interop.common

import com.mad.interop.scopes.AppScope
import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.optional.ForScope
import dagger.Module
import dagger.Provides
import javax.inject.Inject

open class RealDependency(private val qualifier: String) : Dependency {
  override fun doWork(): String {
    return qualifier
  }
}

// @Inject-constructor contribution

//@ContributesBinding(AppScope::class, boundType = Dependency::class)
//@ForScope(AppScope::class)
//class AppDependency @Inject constructor() : RealDependency("AppScope")
//
//@ContributesBinding(LoggedInScope::class, boundType = Dependency::class)
//@ForScope(LoggedInScope::class)
//class LoggedInDependency @Inject constructor() : RealDependency("LoggedInScope")

// @Provides-method contribution
@Module
@ContributesTo(AppScope::class)
object AppDependencyModule {
  @Provides
  @ForScope(AppScope::class)
  fun provideAppDependency(): Dependency = RealDependency("AppScope")
}

@Module
@ContributesTo(LoggedInScope::class)
object LoggedInDependencyModule {
  @Provides
  @ForScope(LoggedInScope::class)
  fun provideLoggedInDependency(): Dependency = RealDependency("LoggedInScope")
}
