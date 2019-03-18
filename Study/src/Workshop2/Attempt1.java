package Workshop2;

class Count extends Thread {

    // number of increments per thread
    static int N = 1000;

    // shared data
    static volatile int counter = 0;
    static int turn = 0;
    
    // protocol variables
    // ...

    public void run() {
      int temp;
      for (int i = 0; i < N; i++) {
        // non-critical section

        // pre-protocol section

        // critical section
        temp = counter;
        counter = temp + 1;

        // post-protocol section
      }
    }

    public static void main(String[] args) {
      Count p = new Count();
      Count q = new Count();
      p.start();
      q.start();
      try { p.join(); q.join(); }
      catch (InterruptedException e) { }
      System.out.println("The final value of the counter is " + counter);
    }
}
