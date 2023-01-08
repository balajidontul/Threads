package Threads.Examples;

public class MultipleThread {

    static NewThreadWithConstructor nw1;
    static NewThreadWithConstructor nw2;
    static NewThreadWithConstructor nw3;

    public static void main(String[] args) {
        nw1 = new NewThreadWithConstructor("one ");
        nw2 = new NewThreadWithConstructor("Two ");
        nw3 = new NewThreadWithConstructor("Three ");
        

        nw1.t.start();
        nw2.t.start();
        nw3.t.start();


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Main Thread Exiting...");
    }
}
