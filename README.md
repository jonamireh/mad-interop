Metro <-> Anvil+Dagger Interop Example

The goal of this project is to have a build param that will configure the project to use metro OR anvil+dagger. Regardless of which config is used the tests should pass.

Test's can be run using:

./gradlew :app:test -Pmad.di="AnvilDagger" && ./gradlew :app:test -Pmad.di="Metro"
