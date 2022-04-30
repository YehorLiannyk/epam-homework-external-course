package main.ua.advanced.practice6.observer.git;

import java.util.ArrayList;
import java.util.List;

public class WebHookClass implements WebHook {
    private String branchName;
    private Event.Type type;
    private List<Event> events = new ArrayList<>();

    public WebHookClass(String branchName, Event.Type type) {
        this.branchName = branchName;
        this.type = type;
    }

    @Override
    public String branch() {
        return branchName;
    }

    @Override
    public Event.Type type() {
        return type;
    }

    @Override
    public List<Event> caughtEvents() {
        return events;
    }

    @Override
    public void onEvent(Event event) {
        events.add(event);
    }
}
