// a waiting zone for newly arrived or soon to depart cargo ships

class WaitZone {

    // ship currently occupying wait zone
    private Ship ship;

    private String label;

    WaitZone(String label) {
        this.ship = null;
        this.label = label;
    }

    // handle arrival of a newShip (from producer)
    synchronized void arrive(Ship newShip)
        throws InterruptedException {
        while (this.ship != null) {
            wait();
        }

        this.ship = newShip;

        System.out.println(newShip.toString() + " arrives at " + this.toString());

        notifyAll();
    }

    // handle departure of current ship (to consumer)
    synchronized Ship depart()
        throws InterruptedException {
        while (this.ship == null) {
            wait();
        }

        Ship ship = this.ship;
        this.ship = null;

        System.out.println(ship.toString() + " departs " + this.toString());

        notifyAll();

        return ship;
    }

    // handle release of newShip by pilot
    synchronized void releaseShip(Ship newShip, Pilot pilot)
        throws InterruptedException {
        while (this.ship != null) {
            wait();
        }

        this.ship = newShip;

        System.out.println(pilot.toString() + " releases " + newShip.toString() + ".");

        notifyAll();
    }

    // handle acquisition of current ship by pilot
    synchronized Ship acquireShip(Pilot pilot)
        throws InterruptedException {
        while (this.ship == null) {
            wait();
        }

        Ship ship = this.ship;
        this.ship = null;

        System.out.println(pilot.toString() + " acquires " + ship.toString() + ".");

        notifyAll();

        return ship;
    }

    public String toString() {
        return this.label + " zone";
    }
}
