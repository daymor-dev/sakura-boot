---
model: claude-sonnet-4-20250514
---

# Implement Feature for Sakura Boot

Based on issue #$ARGUMENTS:

1. Review issue: `gh issue view $ARGUMENTS`
2. Check current branch: `git branch --show-current`
3. Create feature branch: `git checkout -b feature/issue-$ARGUMENTS-description`
4. Analyze which modules are affected
5. Review existing implementations for patterns
6. Plan implementation following framework conventions
7. Implement with:
    - Comprehensive Javadoc (with examples)
    - Unit tests in src/test and sakura-boot-test
    - Integration and functional tests in sakura-boot-test
    - Update example projects
8. Update Antora documentation
9. Run tests: `./gradlew allTest`
10. Format code: `./gradlew spotlessApply`

CRITICAL: Maintain backward compatibility!