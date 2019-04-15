// The berth at which cargo ships dock to unload their cargo.
// Protected by a shield that may be activated/deactivated by an operator.

public class Berth {

    // ship currently docked in berth
    private Ship ship = null;

    // flag for shield
    private boolean shieldActive = false;

    Berth() {}

    // dock newShip in berth
    synchronized void dock(Ship newShip)
        throws InterruptedException {
        while (this.ship != null || this.shieldActive) {
            wait();
        }

        this.ship = newShip;
        System.out.println(newShip.toString() + " docks at " + this.toString() + ".");
        notifyAll();
    }

    // unload currently docked ship
    synchronized void unload()
        throws InterruptedException {
        while (this.ship == null || !this.ship.getFull()) {
            wait();
        }

        this.ship.setFull(false);
        System.out.println(this.ship.toString() + " being unloaded.");
        notifyAll();
    }

    // undock currently docked ship
    synchronized Ship undock()
        throws InterruptedException {
        while (this.ship == null || this.ship.getFull() || this.shieldActive) {
            wait();
        }

        Ship ship = this.ship;
        this.ship = null;
        System.out.println(ship.toString() + " undocks from " + this.toString() + ".");
        notifyAll();

        return ship;
    }

    // activate shield
    synchronized void activateShield() {
        this.shieldActive = true;
        System.out.println("Shield is activated.");
        notifyAll();
    }

    // deactivate shield
    synchronized void deactivateShield() {
        this.shieldActive = false;
        System.out.println("Shield is deactivated.");
        notifyAll();
    }

    public String toString() {
        return "berth";
    }
}
