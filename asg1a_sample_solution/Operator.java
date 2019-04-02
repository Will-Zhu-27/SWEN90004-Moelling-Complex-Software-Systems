// activates and deactivates the berth shield

public class Operator extends Thread {
    private Berth berth;

    Operator(Berth berth) {
        this.berth = berth;
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                sleep(Params.debrisLapse());

                this.berth.activateShield();

                sleep(Params.DEBRIS_TIME);

                this.berth.deactivateShield();
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
