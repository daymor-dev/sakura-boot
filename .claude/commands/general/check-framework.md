---
model: claude-sonnet-4-20250514
---

# Check Framework Compliance

Verify that current changes follow Sakura Boot framework standards:

1. **Code Structure Check:**
    - Module follows sakura-boot-[name] convention
    - Package structure: dev.daymor.sakuraboot.[module]
    - Proper separation of API and implementation

2. **Javadoc Verification:**
    - All public classes have class-level Javadoc
    - All public methods documented
    - Examples included in Javadoc
    - @since tags present
    - @author tags present

3. **Testing Coverage:**
    - Unit tests in src/test/java and in [module]-test
    - Integration tests in sakura-boot-test/sakura-boot-integration-test
    - Functional tests in sakura-boot-test/sakura-boot-functional-test
    - Test naming conventions followed

4. **Framework Patterns:**
    - Uses existing base classes/interfaces
    - Follows dependency injection patterns
    - Proper use of Spring annotations
    - Lombok used appropriately

5. **Documentation:**
    - Antora docs updated in /docs
    - README.md updated if needed
    - Examples provided

6. **Build Verification:**
   ```bash
   ./gradlew clean build
   ./gradlew allTest
   ./gradlew spotlessCheck
   ./gradlew :docs:antora
   ```

Report any violations found!