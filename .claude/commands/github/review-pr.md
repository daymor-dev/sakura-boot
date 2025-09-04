---
model: claude-sonnet-4-20250514
---

# Review Pull Request (Read-Only)

Review PR #$ARGUMENTS without making changes:

1. Fetch PR: `gh pr checkout $ARGUMENTS`
2. Review changes: `git diff develop...HEAD`
3. Check for:
    - Adherence to Sakura Boot patterns
    - Javadoc completeness with examples
    - Test coverage (unit, integration, functional)
    - Example projects updates
    - Antora documentation updates
    - Backward compatibility
    - Proper use of framework abstractions
4. Run tests locally: `./gradlew allTest`
5. Check documentation build: `./gradlew :docs:antora`
6. Prepare review comments
7. DO NOT modify any files