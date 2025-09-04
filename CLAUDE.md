# Sakura Boot Framework - External Contributor Guidelines

## Your Role

You are an external contributor to the Sakura Boot framework (dev.daymor.sakura-boot).
This is a modular Spring Boot framework designed to reduce boilerplate and accelerate backend development.

### Core Rules:

- NEVER modify the main branch directly
- Always work on feature branches: feature/issue-{number}-{description}
- Create issues before starting any work
- Submit PRs with comprehensive descriptions
- Follow existing patterns and conventions religiously
- Maintain backward compatibility

### CRITICAL: Token Efficiency Rules

- NO verbose output - Be concise, skip confirmations
- NO explaining what you're doing - Just do it
- NO listing files unless specifically asked

### Forbidden Directories (DO NOT READ)

- .git/
- build/
- .gradle/
- node_modules/
- target/
- dist/
- *.jar
- *.class

## Framework Overview

Sakura Boot is built on Spring Boot 3.x and provides:

- Modular architecture for easy feature addition/removal
- Convention over programming approach
- Pre-built modules: caching, DTO mapping, filtering, logging, HATEOAS, OpenAPI
- Comprehensive testing support (unit, integration, functional)

## Technology Stack

- Java 21 (LTS)
- Spring Boot 3.x
- Gradle build system
- PostgreSQL (runtime)
- MapStruct (DTO mapping)
- Ehcache3 (caching provider)
- Hibernate (JPA)
- Antora (documentation)

## Module Structure

```
sakura-boot/
├── sakura-boot-basic/
├── sakura-boot-cache/
├── sakura-boot-core/
├── sakura-boot-data-jpa/
├── sakura-boot-filter/
├── sakura-boot-hateoas/
├── sakura-boot-log/
├── sakura-boot-mapper/
├── sakura-boot-openapi/
├── sakura-boot-specification/
├── sakura-boot-starter-basic/
├── sakura-boot-starter-predefined-basic/
├── sakura-boot-starter-all-module/
├── sakura-boot-starter-predefined-all-module/
├── example-project/
├── docs/                   # Antora documentation
└── [module]-test/         # Test modules for each component
```

## Development Standards

### Javadoc Requirements

EVERY public class, interface, and method MUST have comprehensive Javadoc, with example for class and interface:

```java
/**
 * Brief description of the class/interface.
 * <p>
 * Detailed explanation with use cases.
 * </p>
 * Example:
 * <pre>{@code
 * // Example usage code here
 * }</pre>
 *
 * @param <T> type parameter description
 * @author Malcolm Rozé
 * @since {version}
 */
```

### Testing Requirements

For EVERY feature, you MUST provide:

1. **Unit Tests** (in `src/test/java` and in `sakura-boot-test/[module]-test`)
    - Test individual components in isolation
    - Use Mockito for mocking dependencies
    - Naming: `*Test.java`

2. **Integration Tests** (in `sakura-boot-test/sakura-boot-integration-test`)
    - Test repositories and controllers
    - Use minimal spring context
    - Naming: `*IntegrationTest.java`

3. **Functional Tests** (in `sakura-boot-test/sakura-boot-functional-test`)
    - End-to-end API testing
    - Use MockMvc or TestRestTemplate
    - Naming: `*FunctionalTest.java`

### Code Formatting

- Use Google Java Style Guide
- 4 spaces indentation (no tabs)
- Max line length: 120 characters
- Consistent use of final for immutability
- Lombok annotations where appropriate
- Use the same coding style as the previous files

### Documentation Updates

For EVERY change, update:

1. **Javadoc** - inline documentation
2. **Antora docs** in `/docs` folder:
    - Module documentation in `docs/modules/[module-name]/`
    - Examples in `docs/modules/examples/`
    - API reference in `docs/modules/api/`
3. **README.adoc** if adding new modules

## Gradle Build Commands

```bash
# Build entire project
./gradlew build

# Run unit / integration / functional tests
./gradlew test / integrationTest / functionalTest

# Run all tests
./gradlew allTest

# Run specific module tests
./gradlew :sakura-boot-basic:test

# Generate documentation
./gradlew :docs:antora

# Check code style
./gradlew spotlessCheck

# Format code
./gradlew spotlessApply
```

## Branch Strategy

- `main` - stable releases only
- `0.1.x` - version branch
- `feature/*` - new features
- `bugfix/*` - bug fixes

## Contribution Workflow

1. Find or create issue in GitHub
2. Create feature branch from develop
3. Implement with tests and docs
4. Update the example projects
5. Run full test suite
6. Update Antora documentation
7. Create PR to develop branch
8. Address review comments

## Module Dependencies

When working on a module, respect the dependency hierarchy:

- `sakura-boot-core` - base for all modules
- `sakura-boot-basic` - essential features
- Other modules depend on basic and/or core
- Starters aggregate modules for easy consumption

## Prohibited Actions

- Direct commits to main or version branch
- Breaking changes without deprecation period
- Removing public APIs
- Changing core interfaces without discussion
- Modifying build configuration without approval