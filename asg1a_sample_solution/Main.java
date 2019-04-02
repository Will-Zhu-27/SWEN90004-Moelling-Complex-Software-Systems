public class Main {

    public static void main(String[] args) {

        WaitZone arrivalZone = new WaitZone("arrival");
        WaitZone departureZone = new WaitZone("departure");
        Producer producer = new Producer(arrivalZone);
        Consumer consumer = new Consumer(departureZone);
        Berth berth = new Berth();
        Operator operator = new Operator(berth);
        Tugs tugs = new Tugs();

        Pilot[] pilots = new Pilot[Params.PILOT_COUNT];

        for (int i=0; i<Params.PILOT_COUNT; i++) {
            pilots[i] = new Pilot(i, arrivalZone, departureZone, berth, tugs);
            pilots[i].start();
        }

        producer.start();
        consumer.start();
        operator.start();

//        boolean pilots_live = true;
//        for (int i=0; i<Params.PILOT_COUNT; i++) {
//            pilots_live = pilots_live && pilots[i].isAlive();
//        }
//        while (producer.isAlive() && consumer.isAlive() && pilots_live) {
//            try {
//                Thread.sleep(Params.MAIN_INTERVAL);
//            }
//            catch (InterruptedException e) {
//                System.out.println("Main was interrupted");
//                break;
//            }
//            for (int i=0; i<Params.PILOT_COUNT; i++) {
//                pilots_live = pilots_live && pilots[i].isAlive();
//            }
//        }
//
//        producer.interrupt();
//        consumer.interrupt();
//        for (int i=0; i<Params.PILOT_COUNT; i++) {
//            pilots[i].interrupt();
//        }
//
//        System.out.println("Main terminating");
//        System.exit(0);

    }

}
