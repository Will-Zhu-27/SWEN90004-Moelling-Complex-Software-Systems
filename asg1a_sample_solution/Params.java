import java.util.Random;

class Params {

    // the number of tugs available
    final static int TUG_COUNT = 8;

    // the number of pilots available
    final static int PILOT_COUNT = 3;

    // the number of tugs required for docking
    final static int DOCKING_TUGS = 3;

    // the number of tugs required for undocking
    final static int UNDOCKING_TUGS = 2;

    // the time taken to travel between a wait zone and the berth
    final static int TRAVEL_TIME = 400;

    // the duration of the docking process
    final static int DOCKING_TIME = 800;

    // the duration of the undocking process
    final static int UNDOCKING_TIME = 600;

    // the duration of the unloading process
    final static int UNLOAD_TIME = 1000;

    // the maximum amount of time between debris storms
    private final static int MAX_DEBRIS_INTERVAL = 3000;

    // the duration of a debris storm
    final static int DEBRIS_TIME = 600;

    // the maximum amount of time between ship arrivals
    private final static int MAX_ARRIVE_INTERVAL = 1000;

    // the maximum amount of time between ship departures
    private final static int MAX_DEPART_INTERVAL = 1000;

    static int debrisLapse() {
        Random random = new Random();
        return random.nextInt(MAX_DEBRIS_INTERVAL);
    }

    static int arrivalLapse() {
        Random random = new Random();
        return random.nextInt(MAX_ARRIVE_INTERVAL);
    }

    static int departureLapse() {
        Random random = new Random();
        return random.nextInt(MAX_DEPART_INTERVAL);
    }

}
