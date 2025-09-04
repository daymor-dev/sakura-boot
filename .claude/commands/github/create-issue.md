---
model: claude-haiku-3-5-20241022
---

# Create GitHub Issue

Create a new GitHub issue based on the user's description: $ARGUMENTS

1. **Analyze the Request:**
    - Understand what the user wants to implement or fix
    - Identify the affected modules
    - Determine the type: feature, bug, enhancement, documentation

2. **Structure the Issue:**
    - Create a clear, actionable title
    - Write detailed description
    - Add acceptance criteria
    - Include technical details
    - Suggest implementation approach

3. **Format for GitHub:**
   ```markdown
   ## Description
   [Clear description of the feature/bug]
   
   ## Motivation
   [Why this is needed]
   
   ## Affected Modules
   - [ ] sakura-boot-[module]
   - [ ] Tests needed
   - [ ] Documentation update needed
   
   ## Acceptance Criteria
   - [ ] [Specific requirement 1]
   - [ ] [Specific requirement 2]
   
   ## Technical Details
   [Implementation suggestions]
   
   ## Testing Requirements
   - Unit tests for...
   - Integration tests for...
   - Functional tests for...
   ```

4. **Create the Issue:**
   ```bash
   gh issue create \
     --title "[Type] Brief description" \
     --body "..." \
     --label "enhancement,needs-triage" \
     --milestone "v0.2.0"
   ```

5. **Report the Issue Number:**
    - Show the created issue number and URL

Remember to:

- Use clear, actionable language
- Include all context from the discussion
- Add appropriate labels
- Link related issues if any exist