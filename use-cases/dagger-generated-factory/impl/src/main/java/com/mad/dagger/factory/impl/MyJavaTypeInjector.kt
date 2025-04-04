package com.mad.dagger.factory.impl

import com.mad.dagger.factory.MyJavaType
import com.mad.interop.scopes.AppScope
import com.squareup.anvil.annotations.ContributesTo
import javax.inject.Inject

class MyJavaTypeInjector @Inject constructor(
  val myJavaType: MyJavaType
)

@ContributesTo(AppScope::class)
interface MyJavaTypeAccessor {
  val myJavaType: MyJavaType
  val myJavaTypeInjector: MyJavaTypeInjector
}