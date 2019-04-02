// continuously produces cargo ships in arrival zone

public class Producer extends Thread {

    private WaitZone waitZone;

    Producer(WaitZone waitZone) {
        this.waitZone = waitZone;
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                Ship ship = Ship.getNewShip();
                this.waitZone.arrive(ship);

                sleep(Params.arrivalLapse());
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
