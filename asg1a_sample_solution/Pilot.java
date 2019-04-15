// pilot, responsible for docking and undocking ships

public class Pilot extends Thread {

    // unique ID for this pilot
    private int id;

    // entities that the pilot interacts with
    private WaitZone arrivalZone;
    private WaitZone departureZone;
    private Berth berth;
    private Tugs tugs;

    Pilot(int id, WaitZone arrivalZone, WaitZone departureZone,
          Berth berth, Tugs tugs) {
        this.id = id;
        this.arrivalZone = arrivalZone;
        this.departureZone = departureZone;
        this.berth = berth;
        this.tugs = tugs;
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                // board ship, acquire tugs, pilot to berth and dock
                Ship ship = this.arrivalZone.acquireShip(this);
                tugs.acquire(Params.DOCKING_TUGS, this);
                sleep(Params.TRAVEL_TIME);
                sleep(Params.DOCKING_TIME);
                berth.dock(ship);
                tugs.release(Params.DOCKING_TUGS, this);

                // unload cargo from ship
                sleep(Params.UNLOAD_TIME);
                this.berth.unload();

                // acquire tugs, undock from berth, pilot to departure
                // zone, release tugs and disembark
                tugs.acquire(Params.UNDOCKING_TUGS, this);
                sleep(Params.UNDOCKING_TIME);
                ship = berth.undock();
                sleep(Params.TRAVEL_TIME);
                departureZone.releaseShip(ship, this);
                tugs.release(Params.UNDOCKING_TUGS, this);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }

    public String toString() {
        return "pilot " + this.id;
    }


}
