---
model: claude-sonnet-4-20250514
---

# Collaborate on Sakura Boot

You will collaborate on the Sakura boot framework:

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