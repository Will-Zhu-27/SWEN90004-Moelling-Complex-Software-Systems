// continuously removes ships from departure zone

public class Consumer extends Thread {

    private WaitZone waitZone;

    Consumer(WaitZone waitZone) {
        this.waitZone = waitZone;
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                Ship ship = this.waitZone.depart();
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
