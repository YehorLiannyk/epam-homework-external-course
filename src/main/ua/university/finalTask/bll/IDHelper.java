package main.ua.university.finalTask.bll;

import java.util.Random;

public class IDHelper {
    private static final int startIDNumber = 100000;
    private static final int endIDNumber = 999999;

    public static int getFreeID() {
        return generateNewID();
    }

    private static boolean isThisIDFree(int id) {
        ///
        //
        //
        ///
        return true;
    }

    private static int generateNewID() {
        Random random = new Random();
        int id = startIDNumber + random.nextInt((endIDNumber - startIDNumber) + 1); // example: 100 + rand(999 - 100 + 1) => (max = 999), (min = 100)
        while(!isThisIDFree(id))
            id = startIDNumber + random.nextInt((endIDNumber - startIDNumber) + 1); // example: 100 + rand(999 - 100 + 1) => (max = 999), (min = 100)
        return id;
    }

    public static int getStartIDNumber() {
        return startIDNumber;
    }

    public static int getEndIDNumber() {
        return endIDNumber;
    }


}
