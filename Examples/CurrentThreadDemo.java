package Threads.Examples;

//controlling the main thread
public class CurrentThreadDemo {
    public static void main(String[] args) {

        //get current thread data
        Thread t = Thread.currentThread();
        System.out.println( "Current thread :" + t);

        t.setName("My Thread");
        System.out.println("After name Change :" + t);

        try {
            Thread.sleep(1000); //1 Second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
