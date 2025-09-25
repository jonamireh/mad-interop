package com.mad.dagger.factory;

import com.mad.interop.common.Dependency;
import com.mad.interop.scopes.LoggedInScope;
import com.squareup.anvil.annotations.optional.ForScope;

import javax.inject.Inject;

public class LoggedInDependencyConsumer {
  private Dependency mQualifiedDependency;
  @Inject
  public LoggedInDependencyConsumer(
    @ForScope(scope = LoggedInScope.class) Dependency qualifierDependency
  ) {
    this.mQualifiedDependency = qualifierDependency;
  }

  public void doWork() {
    mQualifiedDependency.doWork();
  }
}
