/**
 * 
 * @author yuqiangz@student.unimelb.edu.au
 *
 */
public class Tugs {
	private int total;
	private volatile int availableNum;
	Tugs(int total) {
		this.total = total;
		availableNum = total;
	}
	
	public int getAvailableNum() {
		return availableNum;
	}
	
	public synchronized void minusAvailableNum(int num, int pilotId) {
		while (availableNum < num) {
			try{
				wait();
			}catch(InterruptedException e) {
			}
		}
		availableNum -= num;
		System.out.println("pilot " + pilotId + " acquires " + num + " tugs (" + availableNum + " available).");
	}
	
	public synchronized void plusAvailableNum(int num, int pilotId) {
		availableNum += num;
		System.out.println("pilot " + pilotId + " releases " + num + " tugs (" + availableNum + " available).");
		notify();
	}
}