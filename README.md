Metro <-> Anvil+Dagger Interop Example

The goal of this project is to demonstrate how several "use cases" can be supported by both a Metro and Anvil+Dagger configuration. Refactoring to these common use cases supported by both configurations allows migrations to happen more gradually instead of in a single large change. The project has a DiConventionPlugin which allows the build to toggle using "AnvilDagger" or "Metro" as the di implementation for the entire project.

## Running Tests

Test's can be run using:

```shell
./gradlew :app:test -Pmad.di="AnvilDagger"
./gradlew :app:test -Pmad.di="Metro"
```
