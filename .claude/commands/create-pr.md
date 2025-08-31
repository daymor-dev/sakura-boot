# Create Pull Request for Sakura Boot

Follow these steps to create a PR:

1. Ensure all tests pass: `./gradlew clean test`
2. Check code formatting: `./gradlew spotlessCheck`
3. Apply formatting if needed: `./gradlew spotlessApply`
4. Review the diff: `git diff develop...HEAD`
5. Create descriptive commit messages following conventional commits:
    - feat: new feature
    - fix: bug fix
    - docs: documentation only
    - style: formatting changes
    - refactor: code restructuring
    - test: adding tests
6. Push branch: `git push -u origin HEAD`
7. Create PR using: `gh pr create --base develop --title "[Issue #] Feature description" --body "..."`
8. Include in PR description:
    - Fixes #[issue number]
    - Description of changes
    - Testing performed (unit, integration, functional)
    - Breaking changes (if any)
    - Documentation updates
    - Checklist:
        - [ ] Tests added/updated
        - [ ] Example projects updated
        - [ ] Javadoc with examples added
        - [ ] Antora docs updated
        - [ ] Code formatted with spotlessApply
        - [ ] Backward compatibility maintained