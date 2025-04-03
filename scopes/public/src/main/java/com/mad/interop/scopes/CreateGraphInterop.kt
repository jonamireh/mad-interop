package com.mad.interop.scopes

import java.lang.reflect.Method
import java.lang.reflect.Modifier
import kotlin.reflect.KClass

const val DAGGER_PREFIX = "Dagger"

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

inline fun <reified T : Any> attemptCreateClass(graphClass: KClass<T>) : Class<out T>? {
  val packageName = graphClass.java.`package`.name
  val simpleName = graphClass.simpleName
  val generatedDaggerClassName = "$packageName.$DAGGER_PREFIX$simpleName"
  return try {
    Class.forName(generatedDaggerClassName) as Class<out T>
  } catch (e: Exception) {
    null
  }
}
