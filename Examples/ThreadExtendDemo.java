package Threads.Examples;

public class ThreadExtendDemo {

    public static void main(String[] args) {
        NewThreadExtend newThreadExtend = new NewThreadExtend();
        newThreadExtend.setName("Run method Thread ");
        newThreadExtend.start();

        Thread.currentThread().setName("Main method Thread ");
        for (int i = 5; i >0; i--) {
            System.out.println(Thread.currentThread().getName()+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Exiting Main Thread");
    }
}
