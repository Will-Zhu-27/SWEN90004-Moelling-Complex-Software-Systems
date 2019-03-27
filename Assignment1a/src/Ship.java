/**
 * A cargo ship, with a unique id, that arrives at the space station to deliver
 * its cargo.
 *
 * @author ngeard@unimelb.edu.au
 *
 */

public class Ship {
	/**
	 * a unique identifier for this cargo ship.
	 */
	private int id;
	/**
	 * the next ID to be allocated.
	 */
	private static int nextId = 1;
	/**
	 * a flag indicating whether the ship is currently loaded.
	 */
	private Boolean loaded;

	/**
	 * Create a new vessel with a given identifier.
	 * 
	 * @param id the id of a new ship.
	 */
	private Ship(int id) {
		this.id = id;
		this.loaded = true;
	}

	/**
	 * Get a new Ship instance with a unique identifier.
	 * 
	 * @return the next new ship.
	 */
	public static Ship getNewShip() {
		return new Ship(nextId++);
	}

	/**
	 * Get ship id.
	 * 
	 * @author yuqiangz@student.unimelb.edu.au
	 * @return id of the ship
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the result that whether the ship is currently loaded.
	 * 
	 * @author yuqiangz@student.unimelb.edu.au
	 * @return a flag indicating whether the ship is currently loaded.
	 */
	public Boolean getLoaded() {
		return loaded;
	}

	/**
	 * Produce an identifying string for the cargo ship.
	 * 
	 * @return ship [id] in form of String.
	 */
	public String toString() {
		return "ship [" + id + "]";
	}

}
