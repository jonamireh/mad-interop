package com.mad.dagger.factory;

import com.mad.interop.common.Dependency;
import com.mad.interop.scopes.AppScope;
import com.squareup.anvil.annotations.optional.ForScope;

import javax.inject.Inject;

public class AppDependencyConsumer {
  private Dependency mQualifiedDependency;
  @Inject
  public AppDependencyConsumer(
    @ForScope(scope = AppScope.class) Dependency qualifierDependency
  ) {
    this.mQualifiedDependency = qualifierDependency;
  }

  public String doWork() {
    return mQualifiedDependency.doWork();
  }
}
