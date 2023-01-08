package Threads.Examples;

public class RunnableThreadDemo {
    public static void main(String[] args) {
    
        NewThread nt = new NewThread();
        Thread t = new Thread(nt,"Run method Thread ");
        t.start();

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
