// A cargo ship

public class Ship {

    // a unique ID for this ship
    private int id;

    // true if the ship is currently carrying cargo
    private boolean isFull;

    // the next ID to be allocated
    private static int nextId = 1;

    private Ship(int id) {
        this.id = id;
        this.isFull = true;
    }

    // get a new Ship instance with unique ID
    static Ship getNewShip() {
        return new Ship(nextId++);
    }

    void setFull(boolean newFull) {
        this.isFull = newFull;
    }

    boolean getFull() {
        return this.isFull;
    }

    public String toString() {
        return "ship [" + this.id + "]";
    }

}
