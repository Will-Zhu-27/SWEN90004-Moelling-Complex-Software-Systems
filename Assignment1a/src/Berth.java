/**
 * 
 * @author yuqiangz@student.unimelb.edu.au
 *
 */
public class Berth {
	private String name;
	private volatile Boolean shield;
	private Ship ship;
	
	Berth(String name) {
		this.name = name;
		ship = null;
		shield = false;
	}
	
	public synchronized void dock(Ship ship) {
		while (this.ship != null || shield == true) {
			try {
				wait();
			}catch(InterruptedException e) {
			}
		}
		this.ship = ship;
		// docking time
		try {
			Thread.sleep(Params.DOCKING_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ship docks at berth
		System.out.println(ship.toString() + " docks at " + this.toString());
		//pilot.releaseTugs(Params.DOCKING_TUGS);
	}
	
	public void undock() {
		try {
			Thread.sleep(Params.UNDOCKING_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ship docks from berth
		System.out.println(ship.toString() + " undocks from berth.");
		ship = null;
	}
	
	public void unload() {
		// ship docks at berth
		System.out.println(ship.toString() + " being unloaded.");
		// unloading time
		try {
			Thread.sleep(Params.UNLOADING_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void waitShieldDeactivate() {
		while(shield == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void setShield(Boolean status) {
		shield = status;
		if (shield == false) {
			notify();
		}
	}
	

	public String toString() {
		return name;
	}
}