---
model: claude-sonnet-4-20250514
---

# Create Sub-Issues for Parent Issue

Break down issue #$ARGUMENTS into manageable sub-tasks:

1. **Fetch Parent Issue:**
   ```bash
   gh issue view $ARGUMENTS --json title,body,labels
   ```

2. **Analyze and Decompose:**
   Think about the implementation and identify distinct tasks:
    - Different possible task with their tests
    - Documentation tasks
    - Review and refactoring tasks

3. **Standard Sub-Issues Structure:**
   For a typical feature, create these sub-issues:

   a) **Implementation Sub-Issue:**
   ```markdown
   Title: CLAUDE - [Sub-Task] Implement [sub-task description] for #$ARGUMENTS
   Body:
   Parent Issue: #$ARGUMENTS
   
   ## Task
   Implement ...
   
   ## Checklist
   - [ ] Create interfaces/classes
   - [ ] Implement business logic
   - [ ] Add Javadoc with examples
   - [ ] unit, integration, functional test
   - [ ] Follow existing patterns
   ```

   b) **Documentation Sub-Issue:**
   ```markdown
   Title: CLAUDE - [Sub-Task] Update documentation for #$ARGUMENTS
   Body:
   Parent Issue: #$ARGUMENTS
   
   ## Task
   Update all documentation
   
   ## Checklist
   - [ ] Update Antora docs
   - [ ] Add usage examples
   - [ ] Update README if needed
   - [ ] Update CHANGELOG.adoc
   ```

4. **Create Sub-Issues with GitHub CLI:**
   ```bash
   # Create each sub-issue and link to parent
   gh issue create \
     --title "CLAUDE - [Sub-Task] Implementation for #$ARGUMENTS" \
     --body "..." \
     --label "sub-task" \
     --assignee "@me"
   
   # Link sub-issues to parent (using issue numbers returned)
   gh issue comment $ARGUMENTS --body "Created sub-tasks:
   - #CLAUDE - [sub-issue-1]: Implementation
   - #CLAUDE - [sub-issue-2]: Documentation"
   ```

5. **Create Tracking Comment:**
   Add a checklist to the parent issue:
   ```markdown
   ## Sub-Tasks
   - [ ] #CLAUDE - [num] Implementation
   - [ ] #CLAUDE - [num] Documentation
   ```

Output the created sub-issue numbers and suggest starting with implementation task.

And remember to not create test only sub-issue. The test must be with the related implementation.