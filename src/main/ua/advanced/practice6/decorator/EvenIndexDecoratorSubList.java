package main.ua.advanced.practice6.decorator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class EvenIndexDecoratorSubList extends BaseDecoratorSubList {

    public EvenIndexDecoratorSubList(List<String> subList) {
        super(subList);
        //just for demonstration
        Decorators.logger.log(Level.INFO, "SubList before removing not even: " + this.subList.toString());
        this.subList = getListWithoutNotEvenIndexes(subList);
        Decorators.logger.log(Level.INFO, "SubList after removing not even: " + this.subList.toString());
    }

    private List<String> getListWithoutNotEvenIndexes(List<String> subList) {
        List<String> evenOnly = new ArrayList<>();
        for (int i = 0; i < subList.size(); i++) {
            if (subList.get(i) != null && i % 2 == 0) {
                evenOnly.add(subList.get(i));
            }
        }
        return evenOnly;
    }
}