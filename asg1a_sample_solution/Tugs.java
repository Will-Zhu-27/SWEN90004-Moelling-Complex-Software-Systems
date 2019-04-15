// set of tugs available for use by pilots docking and undocking ships

class Tugs {

    private int available;

    Tugs() {
        this.available = Params.TUG_COUNT;
    }

    // acquire specified number of tugs
    synchronized void acquire(int number, Pilot pilot)
            throws InterruptedException {
        while (this.available < number) {
            wait();
        }

        this.available -= number;
        System.out.println(pilot.toString() + " acquires " + number +
                " tugs (" + this.available + " available).");

        notifyAll();
    }

    // release specified number of tugs
    synchronized void release(int number, Pilot pilot) {
        this.available += number;
        System.out.println(pilot.toString() + " releases " + number +
                " tugs (" + this.available + " available).");

        notifyAll();
    }
}
