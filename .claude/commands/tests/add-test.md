---
model: claude-sonnet-4-20250514
---

# Add Comprehensive Tests

Add all required test types for: $ARGUMENTS

1. Identify the module and component
2. Create unit tests:
    - Test each public method
    - Use Mockito for dependencies
    - Follow existing test patterns
3. Create integration tests:
    - Test repositories and controllers
    - Test with Spring context
    - Test component interactions
4. Create functional tests:
    - Test REST endpoints
    - Use MockMvc
    - Verify HTTP responses
5. Ensure test naming conventions:
    - *Test.java for unit
    - *IntegrationTest.java for integration
    - *FunctionalTest.java for functional
6. Run all tests: `./gradlew allTest`