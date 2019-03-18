/**
 * The top-level component of the space station docking simulator.
 *
 * It is responsible for:
 *  - creating all the components of the system;
 *  - starting all of the processes;
 *
 * @author ngeard@unimelb.edu.au
 *
 */

public class Main {

    /**
     * The driver of the space station docking simulation
     */

    public static void main(String [] args) throws InterruptedException {

        // generate the locations and tugs
        WaitZone arrivalZone = new WaitZone("arrival");
        WaitZone departureZone = new WaitZone("departure");
        Tugs tugs = new Tugs(Params.NUM_TUGS);
        Berth berth = new Berth("berth");

        // generate the producer, consumer and operator processes
        Producer producer = new Producer(arrivalZone);
        Consumer consumer = new Consumer(departureZone);
        Operator operator = new Operator(berth);

        // create an array trains to hold the pilots
        Pilot[] pilot = new Pilot[Params.NUM_PILOTS];

        // generate and start the individual pilot processes
        for (int i = 0; i < Params.NUM_PILOTS; i++) {
            pilot[i] = new Pilot(i, arrivalZone, departureZone, tugs, berth);
            pilot[i].start();
        }

        // start the remaining processes
        producer.start();
        consumer.start();
        operator.start();

        // join all processes
        for (int i = 0; i < Params.NUM_PILOTS; i++) {
            pilot[i].join();
        }
        producer.join();
        consumer.join();
        operator.join();
    }
}