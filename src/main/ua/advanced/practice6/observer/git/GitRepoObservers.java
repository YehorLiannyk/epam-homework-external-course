package main.ua.advanced.practice6.observer.git;

public class GitRepoObservers {
    public static Repository newRepository(){
        return new RepositoryClass();
    }

    public static WebHook mergeToBranchWebHook(String branchName){
        return new WebHookClass(branchName, Event.Type.MERGE);
    }

    public static WebHook commitToBranchWebHook(String branchName){
        return new WebHookClass(branchName, Event.Type.COMMIT);
    }


}
