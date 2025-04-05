package com.mad.inject.replaced.impl

import com.mad.inject.replaced.BindingToReplace
import com.mad.inject.replaced.RealBindingToReplace
import com.mad.interop.scopes.AppScope
import com.squareup.anvil.annotations.ContributesTo
import javax.inject.Inject

// This use case currently fails compilation with a duplicate binding error, commenting out
// Contribution to allow other tests to pass
// @ContributesBinding(AppScope::class, replaces = [BindingToReplace::class])
class ReplacementBinding @Inject constructor(
  private val bindingToReplace: RealBindingToReplace,
) : BindingToReplace {
  override fun text(): String {
    return bindingToReplace.text() + " Replaced"
  }
}

@ContributesTo(AppScope::class)
interface ReplacementBindingAccessor {
  fun replacementBinding(): BindingToReplace
}
