/**
 * 
 * @author yuqiangz@student.unimelb.edu.au
 *
 */
public class Berth {
	private String name;
	private Boolean shield;
	private Ship ship;
	
	Berth(String name) {
		this.name = name;
		ship = null;
		shield = false;
	}
	
	public synchronized void getShip(Ship ship) {
		while (this.ship != null || shield == true) {
			try {
				wait();
			}catch(InterruptedException e) {
			}
		}
		this.ship = ship;
		System.out.println(ship.toString() + " docks at " + this.toString());
		//pilot.releaseTugs(Params.DOCKING_TUGS);
	}

	public String toString() {
		return name;
	}
}