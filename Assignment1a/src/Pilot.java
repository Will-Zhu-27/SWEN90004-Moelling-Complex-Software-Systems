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
				System.out.println(this.toString() + " accquires " + ship.toString() + ".");
				
				try {
					sleep(Params.TRAVEL_TIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				acquireTugs(Params.DOCKING_TUGS);	
				try {
					sleep(Params.DOCKING_TIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				berth.getShip(ship);
				releaseTugs(Params.DOCKING_TUGS);
				sleep(Params.UNLOADING_TIME);
			}
			
		}
	}
}