/**
 * 
 * @author yuqiangz@student.unimelb.edu.au
 *
 */
public class Operator extends Thread {
	private Berth berth;
	private Boolean activated = false;
	Operator(Berth berth) {
		this.berth = berth;
	}
	
	public void run() {
		while(true) {
			try{
				activated = false;
				System.out.println("Shield is deactivated.");
				// wait random time to activate the shield
				sleep(Params.debrisLapse());
				activated = true;
				System.out.println("Shield is activated.");
				// during these time, shield is activated
				sleep(Params.DEBRIS_TIME);
			} catch(InterruptedException e){
			};	
		}
	}
}