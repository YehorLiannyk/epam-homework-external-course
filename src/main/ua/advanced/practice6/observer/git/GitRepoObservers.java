package main.ua.advanced.practice6.observer.git;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GitRepoObservers {
    public static final Logger logger = Logger.getLogger(GitRepoObservers.class.getName());
    //just for demonstration
    static {
        try {
            Handler fileHandler = new FileHandler("resources/advanced/practice6/" + "git_repo_observer.log");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
