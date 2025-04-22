package com.mad.interop.custom

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.mad.interop.scopes.ContributesMultibindingScoped
import com.squareup.anvil.annotations.optional.ForScope
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

/**
 * Generates the code for [ContributesMultibindingScoped].
 *
 * ```
 * @SingleIn(AppScope::class)
 * @ContributesMultibindingScoped(AppScope::class)
 * class RealAuthenticator @Inject constructor() : Authenticator, Scoped
 * ```
 *
 * Will generate:
 * ```
 * @ContributesTo(AppScope::class)
 * @Module
 * interface SoftwareAmazonTestRealAuthenticatorScoped {
 *     @Binds
 *     @IntoSet
 *     @ForScope(AppScope::class)
 *     fun provideRealAuthenticatorAuthenticatorScoped(
 *         realAuthenticator: RealAuthenticator
 *     ): Scoped
 *
 *     @Binds
 *     fun provideRealAuthenticatorAuthenticator(
 *         realAuthenticator: RealAuthenticator
 *     ): Authenticator
 * }
 * ```
 */
internal class ContributesBindingScopedProcessor(
  private val codeGenerator: CodeGenerator,
  override val logger: KSPLogger,
) : SymbolProcessor, ContextAware {

  override fun process(resolver: Resolver): List<KSAnnotated> {
    resolver
      .getSymbolsWithAnnotation(ContributesMultibindingScoped::class)
      .filterIsInstance<KSClassDeclaration>()
      .filter { clazz ->
        val hasSuperType = clazz.superTypes.any { it.resolve().isScoped() }
        if (hasSuperType) return@filter true

        val annotations = clazz.findAnnotationsAtLeastOne(ContributesMultibindingScoped::class)
        annotations.any { annotation -> boundType(clazz, annotation).isScoped() }
      }
      .onEach {
        checkIsPublic(it)
        checkHasScope(it)
      }
      .forEach { generateComponentInterface(it) }

    return emptyList()
  }

  @Suppress("LongMethod")
  private fun generateComponentInterface(clazz: KSClassDeclaration) {
    val componentPackage = clazz.packageName.asString()
    val componentClassName =
      ClassName(componentPackage, "${clazz.innerClassNames()}ScopedModule")

    val scope = clazz.scope()

    val fileSpec =
      FileSpec.builder(componentClassName)
        .addType(
          TypeSpec.interfaceBuilder(componentClassName)
            .addOriginatingKSFile(clazz.requireContainingFile())
            .addContributesToAnnotation(scope.type.toClassName())
            .addAnnotation(Module::class)
            .addFunction(
              FunSpec.builder("provide${clazz.innerClassNames()}Scoped")
                .addModifiers(KModifier.ABSTRACT)
                .addAnnotation(Binds::class)
                .addAnnotation(IntoSet::class)
                .addAnnotation(
                  AnnotationSpec.builder(ForScope::class)
                    .addMember("scope = %T::class", scope.type.toClassName())
                    .build()
                )
                .apply {
                  val parameterName = clazz.innerClassNames().decapitalize()
                  addParameter(
                    ParameterSpec.builder(name = parameterName, type = clazz.toClassName()).build()
                  )
                }
                .returns(scopedClassName)
                .build()
            )
            .addFunction(
              FunSpec.builder("provide${clazz.innerClassNames()}")
                .addModifiers(KModifier.ABSTRACT)
                .addAnnotation(Binds::class)
                .apply {
                  val parameterName = clazz.innerClassNames().decapitalize()
                  addParameter(
                    ParameterSpec.builder(name = parameterName, type = clazz.toClassName()).build()
                  )
                }
                .returns(
                  boundType(
                    clazz,
                    clazz.findAnnotationsAtLeastOne(ContributesMultibindingScoped::class).single()
                  ).toTypeName()
                )
                .build()
            )
            .build()
        )
        .build()

    fileSpec.writeTo(codeGenerator, aggregating = false)
  }
}
