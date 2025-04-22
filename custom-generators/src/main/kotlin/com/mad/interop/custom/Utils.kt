package com.mad.interop.custom

import com.google.devtools.ksp.isDefault
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSValueArgument
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.kotlinpoet.Annotatable
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import java.util.Locale

internal fun String.decapitalize(): String = replaceFirstChar { it.lowercase(Locale.US) }

internal fun String.capitalize(): String = replaceFirstChar {
  if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString()
}

internal fun <T : Annotatable.Builder<T>> Annotatable.Builder<T>.addContributesToAnnotation(
  clazz: ClassName,
): T = addAnnotation(
  AnnotationSpec.builder(ContributesTo::class)
    .addMember("scope = %T::class", clazz)
    .build()
)

internal inline fun <reified T> KSAnnotation.argumentOfTypeAt(
  context: ContextAware,
  name: String,
): T? {
  return argumentOfTypeWithMapperAt<T, T>(context, name) { _, value -> value }
}

private inline fun <reified T, R> KSAnnotation.argumentOfTypeWithMapperAt(
  context: ContextAware,
  name: String,
  mapper: (arg: KSValueArgument, value: T) -> R,
): R? {
  return argumentAt(name)?.let { arg ->
    val value = arg.value
    context.check(value is T, arg) {
      "Expected argument '$name' of type '${T::class.qualifiedName} but was '${arg.javaClass.name}'."
    }
    (value as T)?.let { mapper(arg, it) }
  }
}

internal fun KSAnnotation.argumentAt(name: String): KSValueArgument? {
  return arguments.find { it.name?.asString() == name }?.takeUnless { it.isDefault() }
}
