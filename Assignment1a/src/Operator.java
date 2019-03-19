/**
 * 
 * @author yuqiangz@student.unimelb.edu.au
 *
 */
public class Operator extends Thread {
	private Berth berth;
	
	Operator(Berth berth) {
		this.berth = berth;
	}
	
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