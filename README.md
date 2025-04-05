# Metro ↔ Anvil+Dagger Interop Use Cases

## Overview

This project demonstrates how several dependency injection "use cases" can be supported by both Metro and Anvil+Dagger configurations. By refactoring to these common use cases, migrations between DI frameworks can happen gradually rather than in a single large change.

The project includes a `DiConventionPlugin` which allows toggling between "AnvilDagger" or "Metro" as the DI implementation for the entire project.

## Use Cases

This project showcases several interoperability patterns:

- **Feature with Graph** - Creating a feature-specific dependency graph
- **Multibinding Contributions** - Contributing to multibinding collections
- **Dagger Generated Factory** - Using Dagger's generated factory classes
- **Inject Replaced Contribution** - Replacing bindings at runtime

## Running Tests

Tests can be run with either DI implementation:

```shell
./gradlew :app:test -Pmad.di="AnvilDagger"

./gradlew :app:test -Pmad.di="Metro"
```
