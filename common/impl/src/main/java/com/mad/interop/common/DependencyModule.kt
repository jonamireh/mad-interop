package com.mad.interop.common

import com.mad.interop.scopes.AppScope
import com.mad.interop.scopes.LoggedInScope
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.optional.ForScope
import dagger.Module
import dagger.Provides

open class RealDependency(private val qualifier: String) : Dependency {
  override fun doWork() {
    println("$qualifier Dependency")
  }
}

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
  fun provideLoggedInDependency(): Dependency = RealDependency("LoggedIn")
}
