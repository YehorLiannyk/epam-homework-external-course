package main.ua.advanced.practice5.task11_airport;

import static main.ua.advanced.practice5.task11_airport.AirportDefaults.*;

public class Aircraft implements Runnable {
    private final int capacity;
    private final String name;
    private int passengerAmount = 0;
    private boolean isAirportGuest;
    private City city;

    public Aircraft(City city, String name, boolean isAirportGuest) {
        this.city = city;
        this.isAirportGuest = isAirportGuest;
        this.name = name;
        this.capacity = getRandomCapacity();
    }

    private int getRandomCapacity() {
        return (int) ((Math.random() * (MAX_AIRCRAFT_CAPACITY - MIN_AIRCRAFT_CAPACITY)) + MIN_AIRCRAFT_CAPACITY);
    }

    @Override
    public void run() {
        synchronized (Airport.DISPATCHER_MONITOR) {
            if (isAirportGuest) {
                runFuncAsAirportGuest();
            } else {
                runFuncAsNotAirportGuest();
            }
        }
    }

    private void runFuncAsNotAirportGuest() {
        final String aircraftFullName = "Aircraft " + name + " which is going to " + city.getName();
        try {
            System.out.println("=====< DEPARTING >=====");
            System.out.println(aircraftFullName + " is on the runway");
            System.out.println("Dear passengers of " + aircraftFullName + " please take your seats!");
            final int terminalTime = Airport.getTerminalTimeForPassengerAmount(passengerAmount);
            System.out.println("Passengers: " + passengerAmount + '/' + capacity + ". Approx. time: " + terminalTime);
            Thread.sleep(terminalTime);
            System.out.println(GOODBYE_MSG + city.getName());
            System.out.println("Distance: " + city.getDistance());
            System.out.println(aircraftFullName + " takes of");
            System.out.println("Releasing the runway...");
            Thread.sleep(RELEASING_TIME); // releasing
            Airport.DISPATCHER_MONITOR.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void runFuncAsAirportGuest() {
        final String aircraftFullName = "Aircraft " + name + " from " + city.getName();
        try {
            System.out.println("=====> Arriving <=====");
            System.out.println(aircraftFullName + " is landing");
            Thread.sleep(1000); //landing
            System.out.println(aircraftFullName + " is successfully landed. Beginning disembarking passengers");
            final int gangwayTime = Airport.getGangwayTimeForPassengerAmount(passengerAmount);
            System.out.println("Passengers: " + passengerAmount + '/' + capacity + ". Approx. time: " + gangwayTime);
            Thread.sleep(gangwayTime);
            System.out.println(WELCOME_MSG);
            System.out.println("Releasing the runway...");
            Thread.sleep(RELEASING_TIME); // releasing
            Airport.DISPATCHER_MONITOR.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPassengerAmount() {
        return passengerAmount;
    }

    public void setPassengerAmount(int passengerAmount) {
        this.passengerAmount = passengerAmount;
    }

    public boolean isAirportGuest() {
        return isAirportGuest;
    }

    public void setAirportGuest(boolean airportGuest) {
        isAirportGuest = airportGuest;
    }

    public String getName() {
        return name;
    }
}
