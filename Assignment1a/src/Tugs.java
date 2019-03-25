/**
 * Represents the total tugs.
 * @author yuqiangz@student.unimelb.edu.au
 *
 */
public class Tugs {
	/**
	 * the num of tugs can be acquired.
	 */
	private volatile int availableNum;
	
	/**
	 * Initialize an object of class Tugs and set its configuration.
	 * @param num the total num of tugs.
	 */
	public Tugs(int num) {
		availableNum = num;
	}
	
	/**
	 * Get the num of available tugs. 
	 * @return the num of available tugs.
	 */
	public int getAvailableNum() {
		return availableNum;
	}
	
	/**
	 * Minus the num of pilots.
	 * @param num the num of tugs needs to minus.
	 * @param pilotId the id of the pilot who handles this operation.
	 */
	public synchronized void minusAvailableNum(int num, int pilotId) {
		while (availableNum < num) {
			try{
				wait();
			}catch(InterruptedException e) {
			}
		}
		availableNum -= num;
		System.out.println("pilot " + pilotId + " acquires " + num +
				" tugs (" + availableNum + " available).");
	}
	
	/**
	 * Add some tugs.
	 * @param num the num of tugs added.
	 * @param pilotId the id of the pilot who handles this operation.
	 */
	public synchronized void plusAvailableNum(int num, int pilotId) {
		availableNum += num;
		System.out.println("pilot " + pilotId + " releases " + num + 
				" tugs (" + availableNum + " available).");
		notify();
	}
}