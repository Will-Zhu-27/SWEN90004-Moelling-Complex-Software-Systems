/**
 * 
 * @author yuqiangz@student.unimelb.edu.au
 *
 */
import java.util.List;
import java.util.ArrayList;

public class WaitZone {
	private String name;
	private volatile List<Ship> shipsInZone;
	//private int numOfShip;
	WaitZone(String name) {
		this.name = name;
		shipsInZone = new ArrayList<Ship>();
	}
	
	public synchronized void arrive(Ship ship) {
		waitZoneIsAvailable();
		shipsInZone.add(ship);
		System.out.println(ship.toString() + " arrives at " + name + " zone.");
		notify();
	}
	
	public synchronized Ship removeAship() {
		//notify(); //???
		while(shipsInZone.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return shipsInZone.remove(0);
	}
	
	public void depart() {
		if (!shipsInZone.isEmpty()) {
			Ship ship = removeAship();
			System.out.println(ship.toString() + " departs " + name + " zone.");
		}
	}
	
	public synchronized Boolean existShip() {
		
		return !shipsInZone.isEmpty();
	}
	
	public synchronized void waitZoneIsAvailable() {
		while (shipsInZone.size() >= Params.WAIT_ZONE_CAPACITY) {
			try {
				wait();// something wrong...maybe?
			} catch (InterruptedException e) {
			};
		}
	}
}