/**
 * 
 * @author yuqiangz@student.unimelb.edu.au
 *
 */
import java.util.List;
import java.util.ArrayList;

public class WaitZone {
	private String name;
	private List<Ship> shipsInZone;
	private int numOfShip;
	WaitZone(String name) {
		this.name = name;
		shipsInZone = new ArrayList<Ship>();
	}
	
	public void arrive(Ship ship) {
		if (numOfShip + 1 <= Params.WAIT_ZONE_CAPACITY) {
			shipsInZone.add(ship);
			System.out.println(ship.toString() + " arrives at " + name);
		} else {
			try{
				wait();// something wrong...maybe?
			} catch(InterruptedException e) {
			};
		}
		
	}
	
	public Ship removeAship() {
		return shipsInZone.remove(0);
	}
	
	public void depart() {
		if (!shipsInZone.isEmpty()) {
			//Ship ship = shipsInZone.remove(0);
			System.out.println(removeAship().toString() + " departs " + name);
			notify();
		}
	}
	
	public Boolean existShip() {
		return !shipsInZone.isEmpty();
	}
}