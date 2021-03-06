import java.util.List;
import java.util.ArrayList;

/**
 * Represents a wait zone.
 * Student name: YUQIANG ZHU, ID: 853912
 * 
 * @author yuqiangz@student.unimelb.edu.au
 *
 */
public class WaitZone {
	/**
	 * the name of a zone.
	 */
	private String name;
	/**
	 * ships in the Zone.
	 */
	private volatile List<Ship> shipsInZone;

	/**
	 * Initialize an object of class and set its configuration.
	 * 
	 * @param name the name of the zone.
	 */
	public WaitZone(String name) {
		this.name = name;
		shipsInZone = new ArrayList<Ship>();
	}

	/**
	 * A ship arrives in the zone.
	 * 
	 * @param ship      which arrives in the zone.
	 * 
	 * @param printFlag true: print the arrival information.
	 */
	public synchronized void arrive(Ship ship, Boolean printFlag) {
		waitZoneIsAvailable();
		shipsInZone.add(ship);
		if (printFlag) {
			System.out.println(ship.toString() + " arrives at " 
					+ name + " zone.");
		}
		notify();
	}

	/**
	 * Remove a ship from the zone.
	 * 
	 * @return a ship from the zone.
	 */
	public synchronized Ship removeAship() {
		Ship ship;
		while (shipsInZone.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ship = shipsInZone.remove(0);
		notifyAll();
		return ship;
	}

	/**
	 * Remove a specified ship from the zone.
	 * 
	 * @param ship needs to be removed in the zone.
	 */
	public synchronized void removeAship(Ship ship) {
		shipsInZone.remove(ship);
		notifyAll();
	}

	/**
	 * Get a ship from the zone but doesn't remove it from the zone.
	 * 
	 * @return a ship from the zone.
	 */
	public synchronized Ship getAship() {
		while (shipsInZone.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return shipsInZone.get(0);
	}

	/**
	 * A ship departs from the zone.
	 */
	public void depart() {
		if (!shipsInZone.isEmpty()) {
			Ship ship = removeAship();
			System.out.println(ship.toString() + " departs " 
					+ name + " zone.");
		}
	}

	/**
	 * Show whether is there any ship in the zone.
	 * 
	 * @return true: there are ships. false: no ship.
	 */
	public synchronized Boolean existShip() {
		return !shipsInZone.isEmpty();
	}

	/**
	 * Wait until the zone can receive a new ship.
	 */
	public synchronized void waitZoneIsAvailable() {
		while (shipsInZone.size() >= Params.WAIT_ZONE_CAPACITY) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
			;
		}
	}
}