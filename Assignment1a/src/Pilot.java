/**
 * Represents a pilot and handles pilot's different operations.
 * @author yuqiangz@student.unimelb.edu.au
 *
 */
public class Pilot extends Thread {
	/**
	 * the unique id of a pilot.
	 */
	private int id;
	/**
	 * pilot needs to arrive at this zone to get a new cargo ship. 
	 */
	private WaitZone arrivalZone;
	/**
	 * when the cargo ship arrives at this spot, pilot can
	 *  be transported off the cargo ship.
	 */
	private WaitZone departureZone;
	/**
	 * tugs pilot can be used.
	 */
	private Tugs tugs;
	/**
	 * the spot for the cargo ship to unload.
	 */
	private Berth berth;
	
	/**
	 * Initialize an object of class Pilot and set its configuration.
	 * @param id the unique of a pilot.
	 * @param arrivalZone the arrival zone.
	 * @param departureZone the departure zone.
	 * @param tugs tugs pilot can be used.
	 * @param berth	the spot for the cargo ship to unload.
	 */
	public Pilot(int id, WaitZone arrivalZone, WaitZone departureZone,
			Tugs tugs, Berth berth) {
		this.id = id;
		this.arrivalZone = arrivalZone;
		this.departureZone = departureZone;
		this.tugs = tugs;
		this.berth = berth;
	}
	
    /**
     * Acquire some tugs.
     * @param num the number of tugs pilot needs.
     */
   	public void acquireTugs(int num) {
   		tugs.minusAvailableNum(num, id);
   	}
   	
   	/**
   	 * Release some tugs.
   	 * @param num the number of tugs pilot wants to release.
   	 */
   	public void releaseTugs(int num) {
   		tugs.plusAvailableNum(num, id);
   	}
   	
   	/**
   	 * Return the "pilot" + id in form of String.
   	 * @return the "pilot" + id in form of String.
   	 */
   	public String toString() {
   		return("pilot " + id);
   	}
	
   	/**
   	 * A pilot repeatedly executes the task.
   	 */
	public void run() {
		Ship ship;
		while(true) {
			//if (arrivalZone.existShip()) {
				ship = arrivalZone.getAship();
				// pilot acquires ship
				System.out.println(this.toString() + " acquires " +
						ship.toString() + ".");
				// pilot acquires 3 tugs
				acquireTugs(Params.DOCKING_TUGS);
				// now ship departs the arrival zone
				arrivalZone.removeAship(ship);
				// travel time
				try {
					sleep(Params.TRAVEL_TIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				/****now ship is in the vicinity of the berth****/
				/* until berth is available, spend docking time, 
					ship docks at berth	*/
				berth.dock(ship);
				// pilot releases 3 tugs
				releaseTugs(Params.DOCKING_TUGS);
				// unload
				berth.unload();			
				// pilot acquire 2 tugs
				acquireTugs(Params.UNDOCKING_TUGS);
				// wait to undocking
				berth.waitShieldDeactivate();								
				// ship docks from berth
				berth.undock();
				
				
				/****now ship is in the vicinity of the berth****/
				// travel time
				try {
					sleep(Params.TRAVEL_TIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				departureZone.waitZoneIsAvailable();
				// pilot releases ship
				System.out.println(this.toString() + " releases " +
						ship.toString() + ".");
				// pilot releases tugs
				releaseTugs(Params.UNDOCKING_TUGS);
			//}	
		}
	}
}