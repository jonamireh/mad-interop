package com.mad.interop.scopes

import java.lang.reflect.Method
import java.lang.reflect.Modifier
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.jvm.isAccessible

const val DAGGER_PREFIX = "Dagger"

inline fun <reified T : Any> createGraphInterop(
  graphClass: KClass<T> = T::class,
  vararg arguments: Any
): T {
  return attemptDaggerComponentCreation(graphClass, *arguments) ?: createMetroGraph(graphClass, *arguments)
}

// named attempt DaggerComponentCreation to allow for fallback to creating metro components when
// project is compiled using metro.
inline fun <reified T : Any> attemptDaggerComponentCreation(
  graphClass: KClass<T> = T::class,
  vararg arguments: Any
): T? {
  val daggerClass = attemptCreateClass(graphClass) ?: return null
  val factoryMethod = daggerClass.findFactory<T>(arguments.size)

  return factoryMethod.invoke(null, *arguments) as? T
}

fun <T : Any> createMetroGraph(graphClass: KClass<T>, vararg arguments: Any): T {
  val companionObject = graphClass.companionObject
    ?: error("No companion object found in $graphClass")

  val companionInstance = graphClass.companionObjectInstance
    ?: error("No companion object instance found in $graphClass")

  // Look for a method named "invoke" that returns an instance of the graph
  val invokeFunction = companionObject
    .declaredFunctions
    .firstOrNull { it.name == "invoke" && it.parameters.size == arguments.size + 1 }
    ?: error("No suitable 'invoke' method found in companion of $graphClass")

  invokeFunction.isAccessible = true
  val result = invokeFunction.call(companionInstance, *arguments)

  if (!graphClass.isInstance(result)) {
    error("Result of 'invoke' is not an instance of $graphClass")
  }

  return result as T
}

inline fun <reified T : Any> attemptDaggerChildComponentCreation(
  parentComponentInstance: Any,
  childGraphClass: KClass<T> = T::class,
  vararg arguments: Any
): T? {
  val factoryMethod = parentComponentInstance::class.java.methods.firstOrNull { method ->
    childGraphClass.java.isAssignableFrom(method.returnType)
  } ?: return null
  factoryMethod.trySetAccessible()
  return factoryMethod.invoke(parentComponentInstance, *arguments) as T
}

inline fun <reified T> Class<*>.findFactory(argCount: Int): Method {
  return methods.first {
    Modifier.isStatic(it.modifiers) &&
      T::class.java.isAssignableFrom(it.returnType) &&
      it.parameterTypes.size == argCount
  }
}

fun <T : Any> attemptCreateClass(graphClass: KClass<T>) : Class<out T>? {
  val packageName = graphClass.java.`package`.name
  val simpleName = graphClass.simpleName
  val generatedDaggerClassName = "$packageName.$DAGGER_PREFIX$simpleName"
  return try {
    Class.forName(generatedDaggerClassName) as Class<out T>
  } catch (e: Exception) {
    null
  }
}
