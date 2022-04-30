package main.ua.advanced.practice6.observer.git;

import java.util.*;
import java.util.logging.Level;

public class RepositoryClass implements Repository {
    private final List<WebHook> subscribers = new ArrayList<>();
    private final Map<String, List<Commit>> commitsList = new HashMap<>(); //branch - commits

    @Override
    public void addWebHook(WebHook webHook) {
        subscribers.add(webHook);
    }

    @Override
    public Commit commit(String branch, String author, String[] changes) {
        Commit commit = new Commit(author, changes);
        GitRepoObservers.logger.log(Level.INFO, "Formed commit: " + commit);
        if (isThereWebHook(Event.Type.COMMIT, branch)) {
            addCommitEvent(branch, commit);
            ;
        }
        return commit;
    }

    private void addCommitEvent(String branch, Commit commit) {
        List<Commit> list = new ArrayList<>();
        list.add(commit);
        if (commitsList.containsKey(branch)) {
            commitsList.get(branch).add(commit);
        } else {
            commitsList.put(branch, list);
        }
        eventHandler(Event.Type.COMMIT, branch, Arrays.asList(commit));
    }

    private WebHook getWebHookFromList(Event.Type type, String branchName) {
        if (isThereWebHook(type, branchName)) {
            for (var item : subscribers) {
                if (item.branch().equals(branchName) && item.type().equals(type))
                    return item;
            }
        }
        return null;
    }

    private boolean isThereWebHook(Event.Type type, String branch) {
        if (subscribers.isEmpty())
            return false;
        for (final WebHook webHook : subscribers) {
            if (webHook.type() == type && webHook.branch().equals(branch))
                return true;
        }
        return false;
    }

    @Override
    public void merge(String sourceBranch, String targetBranch) {
        final Event.Type type = Event.Type.MERGE;
        if (isThereWebHook(type, targetBranch)) {
            final List<Commit> commits = commitsList.get(sourceBranch);
            WebHook webHook = getWebHookFromList(type, targetBranch);
            boolean anotherCommit = isAnotherCommit(commits, webHook);
            if (anotherCommit)
                eventHandler(type, targetBranch, commits);
            else
                GitRepoObservers.logger.log(Level.WARNING, "Didn't merge: there were the same commits: " + commits);
        }
    }

   /* private boolean isAnotherCommit(List<Commit> commits, WebHook webHook) {
        AtomicBoolean anotherCommit = new AtomicBoolean(true);
        webHook.caughtEvents().forEach(event -> {
            if (event.commits().equals(commits))
                anotherCommit.set(false);
        });
        return anotherCommit.get();
    }*/

    private boolean isAnotherCommit(List<Commit> commits, WebHook webHook) {
        return webHook.caughtEvents().stream()
                .noneMatch(event -> event.commits().equals(commits));
    }

    private void eventHandler(Event.Type type, String branch, List<Commit> commits) {
        for (var item : subscribers) {
            if (item.branch().equals(branch) && item.type().equals(type)) {
                final Event event = new Event(type, branch, commits);
                item.onEvent(event);
                GitRepoObservers.logger.log(Level.INFO, "Add event " + event + " to " + item);
            }
        }
    }
}
