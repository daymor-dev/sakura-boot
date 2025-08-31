# Update Antora Documentation

Update documentation for: $ARGUMENTS

1. Navigate to docs folder
2. Identify affected modules
3. Update/create files in:
    - `docs/modules/ROOT/pages/` - main documentation
    - `[module-name]/docs/modules/ROOT/pages/` - module documentation
4. Follow Antora/AsciiDoc conventions:
    - Use .adoc extension
    - Include code examples
    - Cross-reference other modules
5. Build docs: `./gradlew :docs:antora`
6. Verify generated documentation in `build/site/`

Remember to:
- Include code snippets from actual implementation
- Add diagrams if needed
- Update version references