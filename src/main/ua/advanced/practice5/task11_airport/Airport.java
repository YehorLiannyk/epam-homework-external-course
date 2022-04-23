package main.ua.advanced.practice5.task11_airport;


import java.util.concurrent.atomic.AtomicInteger;

public class Airport {
    static final Object DISPATCHER_MONITOR = new Object();
    private Aircraft[] aircraftArr;

    public static void main(String[] args) {
        Airport airport = new Airport();
        airport.aircraftArr = airport.getFilledAircraftArray();

        airport.openAirport();
    }

    public static int getGangwayTimeForPassengerAmount(int passengerAmount) {
        return (passengerAmount * AirportDefaults.GANGWAY_MILLISECONDS_ONE_PASSENGER) / AirportDefaults.GANGWAY_AMOUNT;
    }

    public static int getTerminalTimeForPassengerAmount(int passengerAmount) {
        return (passengerAmount * AirportDefaults.TERMINAL_MILLISECONDS_ONE_PASSENGER) / AirportDefaults.TERMINAL_AMOUNT;
    }

    private void openAirport() {
        while (true) {
            for (int i = 0; i < AirportDefaults.AIRCRAFT_AMOUNT; i++) {
                new Thread(aircraftArr[i]).start();
            }
            resetAircraftArr();
        }
    }

    private void resetAircraftArr() {
        for (int i = 0; i < AirportDefaults.AIRCRAFT_AMOUNT; i++) {
            if (aircraftArr[i].isAirportGuest()) {
                aircraftArr[i].setAirportGuest(false);
            } else {
                aircraftArr[i].setAirportGuest(true);
            }
            final int randomPassengerAmount = getRandomPassengerAmount(aircraftArr[i].getCapacity());
            aircraftArr[i].setPassengerAmount(randomPassengerAmount);
        }
    }

    private Aircraft[] getFilledAircraftArray() {
        Aircraft[] aircraftArray = new Aircraft[AirportDefaults.AIRCRAFT_AMOUNT];
        for (AtomicInteger i = new AtomicInteger(0); i.get() < AirportDefaults.AIRCRAFT_AMOUNT; i.incrementAndGet()) {
            aircraftArray[i.get()] = new Aircraft(getRandomCity(), ("AN-" + i.get()), getRandomGuestStatus());
            final int randomPassengerAmount = getRandomPassengerAmount(aircraftArray[i.get()].getCapacity());
            aircraftArray[i.get()].setPassengerAmount(randomPassengerAmount);
        }
        return aircraftArray;
    }

    private int getRandomPassengerAmount(int maxCapacity) {
        final int minCapacity = maxCapacity / 2;
        return (int) (Math.random() * (maxCapacity - minCapacity) + minCapacity);
    }

    private boolean getRandomGuestStatus() {
        int option = (int) (Math.random() * 2);
        return option != 0;
    }

    private City getRandomCity() {
        int randomIndex;
        do {
            randomIndex = (int) (Math.random() * AirportDefaults.cities.length);
        } while (AirportDefaults.cities[randomIndex] == null);
        return AirportDefaults.cities[randomIndex];
    }

    private int getAircraftIndex(Aircraft aircraft, Aircraft[] aircraftArr) {
        for (int i = 0; i < aircraftArr.length; i++) {
            if (aircraftArr[i].equals(aircraft))
                return i;
        }
        return -1;
    }
}
