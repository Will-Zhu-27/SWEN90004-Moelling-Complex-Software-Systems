/**
 * Periodically activates the shield to protect 
 * the space station from space debris.
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
	 * @param berth needs to be operated.
	 */
	public Operator(Berth berth) {
		this.berth = berth;
	}
	
	/**
	 * repeatedly activate and deactivate the shield.
	 */
	public void run() {
		while(true) {
			try{
				berth.setShield(false);
				sleep(Params.debrisLapse());
				berth.setShield(true);
				sleep(Params.DEBRIS_TIME);
			} catch(InterruptedException e){
			};	
		}
	}
}