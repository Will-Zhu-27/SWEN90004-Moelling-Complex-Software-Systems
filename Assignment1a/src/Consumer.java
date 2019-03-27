/**
 * Consumes unloaded cargo ships from the departure zone.
 * Student name: YUQIANG ZHU, ID: 853912
 * 
 * @author ngeard@unimelb.edu.au
 *
 */

public class Consumer extends Thread {

	/**
	 * the wait zone from which cargo ships depart.
	 */
	private WaitZone departureZone;

	/**
	 * Creates a new consumer for the given wait zone.
	 * 
	 * @param newDepartureZone departureZone
	 */
	Consumer(WaitZone newDepartureZone) {
		this.departureZone = newDepartureZone;
	}

	/**
	 * Repeatedly collect waiting ships from the departure zone.
	 */
	public void run() {
		while (!isInterrupted()) {
			// while (true) {
			try {
				// remove a vessel that is in the departure wait zone
				departureZone.depart();

				// let some time pass before the next departure
				sleep(Params.departureLapse());
			} catch (InterruptedException e) {
				this.interrupt();
			}
		}
		System.out.println("Mention: Consumer thread is interrupted!!!");
	}
}
