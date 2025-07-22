# Metro ↔ Anvil+Dagger Interop

## Overview

This project demonstrates how several dependency injection "use cases" can be supported by both Metro and Anvil+Dagger configurations. By refactoring to these common use cases, migrations between DI frameworks can happen gradually rather than in a single large change.

The project includes a `DiConventionPlugin` which allows toggling between "AnvilDagger" or "Metro" as the DI implementation for the entire project.

## Use Cases

<div style="background-color: #2d2d2d; padding: 15px; border-radius: 5px; margin: 10px 0;">
<details>
<summary><strong>Dependency Graph</strong> (✅)</summary>

```kotlin
@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
interface AppGraph
```
</details>
</div>
<div style="background-color: #2d2d2d; padding: 15px; border-radius: 5px; margin: 10px 0;">
<details>
<summary><strong>Contributed Module</strong> (✅)</summary>
<p>All modules in Metro must be interfaces, that means all bindings must be provided in companion objects. Only modules using ContribtuesTo are compatible with both AnvilDagger and Metro.</p>

```kotlin
@ContributesTo(AppScope::class)
@Module
interface MyModuleProvidedTypeModule {
  @Binds fun bindMyModuleProvidedType(real: RealMyModuleProvidedType): MyModuleProvidedType

  companion object {
    @Provides
    @SingleIn(AppScope::class)
    fun provideMyModuleProvidedType(): RealMyModuleProvidedType = RealMyModuleProvidedType()
  }
}

interface MyModuleProvidedType
class RealMyModuleProvidedType : MyModuleProvidedType
```
</details>
</div>
<div style="background-color: #2d2d2d; padding: 15px; border-radius: 5px; margin: 10px 0;">
<details>
<summary><strong>Multibinding Contributions</strong> (✅)</summary>

```kotlin
@ForScope(AppScope::class)
@SingleIn(AppScope::class)
@ContributesMultibinding(AppScope::class, boundType = Scoped::class)
@ContributesBinding(
  AppScope::class,
  boundType = UseCaseSpecificInterface::class,
  ignoreQualifier = true,
)
class ScopedUseCase @Inject constructor() : Scoped, UseCaseSpecificInterface
```
</details>
</div>

<div style="background-color: #2d2d2d; padding: 15px; border-radius: 5px; margin: 10px 0;">
<details>
<summary><strong>Dagger Generated Factory</strong> (✅)</summary>
<p>Metro is capable of using factories dagger generates for java inject constructors. Prevents all java files from having to be converted to kotlin.</p>

```java
@SingleIn(scope = AppScope.class)
public class MyJavaType {
  @Inject public MyJavaType() {
  }
}
```
</details>
</div>

<div style="background-color: #2d2d2d; padding: 15px; border-radius: 5px; margin: 10px 0;">
<details>
<summary><strong>Feature with Graph</strong> (⚠️)</summary>
Interop for ContributesSubcomponent is not yet complete: https://github.com/ZacSweers/metro/issues/704. 
However in the interim it is possible to double annotate contributed components AND their Factory using
both Metro and Anvil annotations. Note in order for this to work you will have to configure includeAnvil
interop manually. See convention plugin for further details.

```kotlin
annotation class ContributedFeatureScope

@SingleIn(ContributedFeatureScope::class)
@ContributesSubcomponent(
  scope = ContributedFeatureScope::class,
  parentScope = LoggedInScope::class,
)
@ContributesGraphExtension(ContributedFeatureScope::class)
interface ContributedFeatureGraph {
  @ContributesSubcomponent.Factory
  @ContributesGraphExtension.Factory(AppScope::class)
  interface Factory {
    fun create(): ContributedFeatureGraph
  }
}
```
</details>
</div>

<div style="background-color: #2d2d2d; padding: 15px; border-radius: 5px; margin: 10px 0;">
<details>
<summary><strong>Inject Replaced Contribution</strong> (✅)</summary>

```kotlin
interface BindingToReplace

@ContributesBinding(AppScope::class) 
class RealBindingToReplace @Inject constructor() : BindingToReplace

@ContributesBinding(AppScope::class, replaces = [RealBindingToReplace::class])
class ReplacementBinding @Inject constructor(
  private val bindingToReplace: RealBindingToReplace,
) : BindingToReplace 
```
</details>
</div>

<div style="background-color: #2d2d2d; padding: 15px; border-radius: 5px; margin: 10px 0;">
<details>
<summary><strong>Included Modules</strong> (✅)</summary>

```kotlin
class IncludedModuleProvidedType()

@Module
object IncludedObjectModule {
  @Provides
  @SingleIn(AppScope::class)
  fun provideIncludedModuleType(): IncludedModuleProvidedType = IncludedModuleProvidedType()
}

@MergeComponent(AppScope::class, modules = [IncludedObjectModule::class])
interface AppGraph
```
</details>
</div>

## Running Tests

Tests can be run with either DI implementation:

```shell
./gradlew :app:test -Pmad.di="AnvilDagger"

./gradlew :app:test -Pmad.di="Metro"
```
