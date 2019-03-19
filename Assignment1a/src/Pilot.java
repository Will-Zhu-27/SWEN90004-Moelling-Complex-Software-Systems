/**
 * 
 * @author yuqiangz@student.unimelb.edu.au
 *
 */
public class Pilot extends Thread {
	private int id;
	private WaitZone arrivalZone, departureZone;
	private Tugs tugs;
	private Berth berth;
	
	Pilot(int id, WaitZone arrivalZone, WaitZone departureZone, Tugs tugs, Berth berth) {
		this.id = id;
		this.arrivalZone = arrivalZone;
		this.departureZone = departureZone;
		this.tugs = tugs;
		this.berth = berth;
	}
	
    // require tugs
   	public void acquireTugs(int num) {
   		tugs.minusAvailableNum(num, id);
   	}
   	
   	// release tugs
   	public void releaseTugs(int num) {
   		tugs.plusAvailableNum(num, id);
   	}
   	
   	public String toString() {
   		return("pilot " + id);
   	}
	
	public void run() {
		Ship ship;
		while(true) {
			if (arrivalZone.existShip()) {
				ship = arrivalZone.removeAship();
				// pilot acquires ship
				System.out.println(this.toString() + " acquires " + ship.toString() + ".");
				// pilot acquires 3 tugs
				acquireTugs(Params.DOCKING_TUGS);	
				// travel time
				try {
					sleep(Params.TRAVEL_TIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// until berth is available, spend docking time, ship docks at berth
				berth.dock(ship);
				// pilot releases 3 tugs
				releaseTugs(Params.DOCKING_TUGS);
				// begin unload
				berth.unload();
				
				// wait to undock
				berth.waitShieldDeactivate();
				// pilot acquire 2 tugs
				acquireTugs(Params.UNDOCKING_TUGS);
				// ship docks from berth
				berth.undock();
				// travel time
				try {
					sleep(Params.TRAVEL_TIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				departureZone.waitZoneIsAvailable();
				// pilot releases ship
				System.out.println(this.toString() + " releases " + ship.toString() + ".");
				// pilot releases tugs
				releaseTugs(Params.UNDOCKING_TUGS);
			}
			
		}
	}
}