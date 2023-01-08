package Threads.Examples;

public class NewThread implements Runnable {

    @Override
    public void run() {

       for(int i=5; i>0;i--){
        System.out.println(Thread.currentThread().getName()+ i);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       }
       
       System.out.println("Exiting Run method Thread.");
    }
    
}
