package Threads.Examples;

public class NewThreadWithConstructor implements Runnable {
    String name;
    Thread t;

    NewThreadWithConstructor(String threadName){
        name = threadName;
        t = new Thread(this, name);
        System.out.println("New Thread: "+ t);
    }

    @Override
    public void run() {
        for (int i = 5; i >0; i--) {
            System.out.println(Thread.currentThread().getName() + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+ "Exiting...");
    }
}
