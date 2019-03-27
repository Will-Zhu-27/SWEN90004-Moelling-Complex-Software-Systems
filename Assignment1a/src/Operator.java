/**
 * Periodically activates the shield to protect the space station from space
 * debris.
 * Student name: YUQIANG ZHU, ID: 853912
 * 
 * @author yuqiangz@student.unimelb.edu.au
 *
 */
public class Operator extends Thread {
	/**
	 * the berth needs to be operated.
	 */
	private Berth berth;

	/**
	 * Initialize an object of class Operator and set its configuration.
	 * 
	 * @param berth needs to be operated.
	 */
	public Operator(Berth berth) {
		this.berth = berth;
	}

	/**
	 * Repeatedly activate and deactivate the shield.
	 */
	public void run() {
		while (true) {
			try {
				// shield is deactivated
				berth.setShield(false);
				sleep(Params.debrisLapse());

				// shield is activated
				berth.setShield(true);
				sleep(Params.DEBRIS_TIME);
			} catch (InterruptedException e) {
			}
			;
		}
	}
}