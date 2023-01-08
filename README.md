![GitHub top language](https://img.shields.io/github/languages/top/balajidontul/Threads?color=yellow)
# JAVA Threads
## Introduction 
Java provides built-in support for multithreaded programming. A multithreaded programs contains two or more parts that can run concurrently. Each part of such program is called a thread, and each thread defines a seperate path of execution.

Thread exist in several states. A thread can be running. It can be ready to run as soon as it gets CPU time. A running thread can be suspended, which temporarily halts its activity. A suspended thread can be resumed, allowing it to pick up where it left off. A thread can be blocked when waiting for resources. At any time, a thread can be terminated which halts the execution immediately. Once terminated, a thread cannot be resumed 

### Thread Priority 
Java assign to each thread a priority that determines how that thread should be treated with respect to others. Thread priorities are integers that specify the relative priority of one thread to other.

### Synchronization
Because multithreading introduces an asynchronous behavior to your programs, there must be a way for you to enforce synchronicity when you need it . Java implements an elegant twist on age-old model of interprocess synchronization: the monitor. You can think of a monitor as a very small box that can hold only one thread. Once a thread enters a monitor, all other threads must wait until that thread exits the monitor.

Once a thread is inside a synchronized method, no other trade can call any other synchronized method on the same object.

### Messaging 
After you divide your program into separate threads, you need to define how they will communicate with each other. Java’s messaging system allows a thread to enter a synchronized method  on an object, and then wait there until other thread explicitly notify it to came out

### The main Thread
When a Java program starts up, one thread begins running immediately. This is usually called the main thread of your program, because it is the one that is executed when your program begins.

It can be controlled through a **Thread** object. You must obtain a reference to it by calling the method *currentThread()*, which is public static member of Thread.

> Static Thread currentThread()

[Below Code Link](https://github.com/balajidontul/Threads/blob/e9a4ea2ce1348a8ae940c318b99c6d43e4de4b1a/Examples/CurrentThreadDemo.java)

```java
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
```
In this program a reference to the current thread is obtained by calling **currentThread()**, and this reference is stored in the local variable t. the program then calls **setName()** to change the internal name of the thread. **sleep()** used to accomplish the pause for one second between each line ( specify the delay in milliseconds), sleep() method in Thread might throw an **interruptedException** so surround that with try/catch block.

Output:
> Current thread :Thread[main,5,main] <br> After name Change :Thread[My Thread,5,main]

The output displays, in order [name of thread,its priority,name of its group]


### Creating a Thread
Java's multithreading system is built upon the **Thread** class, its methods, and its companion interface **Runnable**.

Java defines two ways in which this can be accomplished:
- implement the Runnable Interface
- extend the Thread class, itself

### Implementing Runnable
- To create a thread is to create a class that implements the **Runnable** interface
- To implement Runnable, a class need only implement a single method called run()
- Inside run(), you will define the code that constitutes the new thread.
- run() can call other method, use other classes, and declare variable, just like main thread can.
- instantiate an object of type **Thread** from within the class that implement Runnable.
- Thread defines several constructors, one that will use is shown below –
> Thread(Runnable threadOb, String threadName)
- After new thread is created, it will not start running until you call its start() method, which is declared within **Thread**

```java
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
```

```java
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
```






