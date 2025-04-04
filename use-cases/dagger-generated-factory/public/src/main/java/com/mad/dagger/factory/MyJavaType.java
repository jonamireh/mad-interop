package com.mad.dagger.factory;

import com.mad.interop.scopes.AppScope;
import com.squareup.anvil.annotations.optional.SingleIn;
import javax.inject.Inject;

@SingleIn(scope = AppScope.class)
public class MyJavaType {

  @Inject public MyJavaType() {

  }
}
