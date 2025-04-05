package com.mad.interop.scopes

object GraphHolder {
  var appGraph: Any? = null
  var loggedInGraph: Any? = null

  fun <T> loggedInGraph(): T = loggedInGraph as T
}
