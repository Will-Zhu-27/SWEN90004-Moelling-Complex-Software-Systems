/**
 * A cargo ship, with a unique id, that arrives at
 * the space station to deliver its cargo.
 *
 * @author ngeard@unimelb.edu.au
 *
 */

public class Ship {

    // a unique identifier for this cargo ship
    private int id;

    // the next ID to be allocated
    private static int nextId = 1;

    // a flag indicating whether the ship is currently loaded
    Boolean loaded;  //Change by Will #####

    // create a new vessel with a given identifier
    private Ship(int id) {
        this.id = id;
        this.loaded = true;
    }

    // get a new Ship instance with a unique identifier
    public static Ship getNewShip() {
        return new Ship(nextId++);
    }
    
    // get ship id added by Will ##########
    public int getId() {
    	return id;
    }

    // get the ship status by Will###########
    public Boolean getLoaded() {
    	return loaded;
    }
    
    // produce an identifying string for the cargo ship
    public String toString() {
        return "ship [" + id + "]";
    }
    
}
