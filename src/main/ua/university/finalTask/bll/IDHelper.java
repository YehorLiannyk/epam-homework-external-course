package main.ua.university.finalTask.bll;

import main.ua.university.finalTask.bll.country.Country;

import java.util.List;
import java.util.Random;

public class IDHelper {
    private static final int startIDNumber = 100000;
    private static final int endIDNumber = 999999;

    public static int getFreeID(List<Country> countries) {
        return generateNewID(countries);
    }

    public static boolean isThisIDFree(int id, List<Country> countries) {
        for (var country : countries)
            if (country.getId() == id)
                return false;

        for (var country : countries)
            if (country.getCities() != null)
                for (var city : country.getCities())
                    if (city.getId() == id)
                        return false;
        return true;
    }

    private static int generateNewID(List<Country> countries) {
        Random random = new Random();
        int id = startIDNumber + random.nextInt((endIDNumber - startIDNumber) + 1);
        while (!isThisIDFree(id, countries))
            id = startIDNumber + random.nextInt((endIDNumber - startIDNumber) + 1);
        return id;
    }

    public static int getStartIDNumber() {
        return startIDNumber;
    }

    public static int getEndIDNumber() {
        return endIDNumber;
    }

}
