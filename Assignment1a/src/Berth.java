/**
 * Represents the berth.
 * 
 * @author yuqiangz@student.unimelb.edu.au
 *
 */
public class Berth {
	/**
	 * represents the name of an object of class Berth.
	 */
	private String name;
	/**
	 *  represents the status of shield, 
	 *  true value means shield is activated.
	 */
	private volatile Boolean shield;
	/**
	 * represents the ship in the berth.
	 */
	private volatile Ship ship;
	
	/**
	 * Initialize an object of class Berth and set its name.
	 * 
	 * @param name
	 *            the name of an object of class Berth.
	 */
	public Berth(String name) {
		this.name = name;
		ship = null;
		shield = false;
	}
	
	/**
	 * A ship requests to dock into the berth.
	 * 
	 * @param ship
	 *            plans to dock into the berth.
	 */
	public synchronized void dock(Ship ship) {
		// wait until there is no ship in berth and shield is deactivated.
		while (this.ship != null || shield == true) {
			try {
				wait();
			}catch(InterruptedException e) {
			}
		}
		
		// ship docks at berth
		this.ship = ship;
		System.out.println(ship.toString() + " docks at " + this.toString());
		// docking time
		try {
			Thread.sleep(Params.DOCKING_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * The ship in the berth plans to undock.
	 */
	public void undock() {
		// wait until shield is deactivated.
		waitShieldDeactivate();
		//begin undock
		System.out.println(ship.toString() + " undocks from berth.");
		ship = null;
		// undocking time
		try {
			Thread.sleep(Params.UNDOCKING_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * The ship in the berth plans to unload cargo.
	 */
	public void unload() {
		System.out.println(ship.toString() + " being unloaded.");
		// unloading time
		try {
			Thread.sleep(Params.UNLOADING_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Wait until the ship is deactivated.
	 */
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
	
	/**
	 * Set the status of shield.
	 * 
	 * @param status
	 *            the new status of shield.
	 */
	public synchronized void setShield(Boolean status) {
		shield = status;
		if (shield == false) {
			System.out.println("Shield is deactivated.");
			notifyAll();
		} else {
			System.out.println("Shield is activated.");
		}
	}
	
	/**
	 * Get the name of the object of class Berth in form of String.
	 * 
	 * @return the name of the object of class Berth in form of String.
	 */
	public String toString() {
		return name;
	}
}