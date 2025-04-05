package com.mad.inject.replaced

import com.mad.interop.scopes.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class RealBindingToReplace @Inject constructor() : BindingToReplace {
  override fun text(): String = "Original Text"
}
