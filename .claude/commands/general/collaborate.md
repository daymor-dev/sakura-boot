---
model: claude-opus-4-1-20250805
---

# Collaborate on Sakura Boot

You will collaborate on the Sakura boot framework
For any action that you do on GitHub be sure to identify yourself has CLAUDE
(in the title of the issue, PR and at the end of a comment)

### When talking about sub-issue:

1. It's refer to an issue that is link to a parent issue
2. The child and parent issue are link with GitHub sub-issue, not via comment
3. Sub-issue are not sub-task in an issue but separate issue

### When working with sub-issue:

1. The parent must have a branch
2. For each sub-issue one branch must be created from the parent branch
3. When creating a PR, the target branch must be the parent branch
4. Never code in the parent branch, only in the sub-issue branch

## If sub-issue exists:

1. Select one sub-issue, that has no conversation first if possible
2. Do the /implement-feature <issue-number> command on the selected sub-issue
3. Do the /create-pr command but ignore the first repetitive task
4. The collaboration task is now finish

## If issue exists:

1. Select one issue, that has no conversation first if possible
2. Do the /create-sub-issues <issue-number> command on the selected issue
3. The collaboration task is now finish

## If pull-request exists:

1. Select one pull-request, that has no conversation first if possible
2. Do the /review-pr <pr-number> command on the selected pull-request
3. The collaboration task is now finish

## If no issue or pull-request exits:

1. Checkout the main branch
2. Do the /check-framework and try to find work that need to be done
    1. If code need to be improved
    2. If tests need to be improved
    3. If the documentation need to be improved
    4. If any other improvement must be done
    5. If no update is needed, do not proceed with the next tasks.
3. Do the /create-issue command related to the change that need to be done
4. The collaboration task is now finish

## If no issue, pull-request exits or any change must be done:

1. Check on the [milestones](https://github.com/daymor-dev/sakura-boot/milestones) page the oldest open milestone
2. In the milestone description find a task that need to be done but don't have related issue
3. Do the /create-issue command on the task that need to be done