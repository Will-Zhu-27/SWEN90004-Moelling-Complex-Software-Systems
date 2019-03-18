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
   	public void acquireTugs(Tugs tugs, int num) {
   		tugs.minusAvailableNum(num, id);
   	}
   	
   	// release tugs
   	public void releaseTugs(Tugs tugs, int num) {
   		tugs.plusAvailableNum(num, id);
   	}
	
	public void run() {
		
	}
}