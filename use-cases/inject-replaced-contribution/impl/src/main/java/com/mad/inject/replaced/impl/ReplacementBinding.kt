package com.mad.inject.replaced.impl

import com.mad.inject.replaced.BindingToReplace
import com.mad.inject.replaced.RealBindingToReplace
import com.mad.interop.scopes.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dev.zacsweers.metro.ContributesBinding
import javax.inject.Inject

@ContributesBinding(AppScope::class, replaces = [BindingToReplace::class])
class ReplacementBinding @Inject constructor(
  private val bindingToReplace: RealBindingToReplace,
) : BindingToReplace {
  override fun text(): String {
    return bindingToReplace.text() + " Replaced"
  }
}

@ContributesTo(AppScope::class)
interface ReplacementBindingAccessor {
  fun replacementBinding(): ReplacementBinding
}
