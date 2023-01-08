![GitHub top language](https://img.shields.io/github/languages/top/balajidontul/Threads?color=yellow)
# JAVA Threads
### Introduction 
Java provides built-in support for multithreaded programming. A multithreaded programs contains two or more parts that can run concurrently. Each part of such program is called a thread, and each thread defines a seperate path of execution.

Thread exist in several states. A thread can be running. It can be ready to run as soon as it gets CPU time. A running thread can be suspended, which temporarily halts its activity. A suspended thread can be resumed, allowing it to pick up where it left off. A thread can be blocked when waiting for resources. At any time, a thread can be terminated which halts the execution immediately. Once terminated, a thread cannot be resumed 

### Thread Priority 
Java assign each thread a priority that determines how that thread should be treated with respect to others. Thread priorities are integers that specify the relative priority of one thread to other.

### Synchronization
Because multithreading introduces an asynchronous behavior to your programs, there must be a way for you to enforce synchronicity when you need it . Java implements an elegant twist on age-old model of interprocess synchronization: the monitor. You can think of a monitor as a very small box that can hold only one thread. Once a thread enters a monitor, all other threads must wait until that thread exits the monitor.

Once a thread is inside a synchronized method, no other trade can call any other synchronized method on the same object.

### Messaging 
After you divide your program into separate threads, you need to define how they will communicate with each other. Java’s messaging system allows a thread to enter a synchronized method  on an object, and then wait there until other thread explicitly notify it to came out

## The main Thread
When a Java program starts up, one thread begins running immediately. This is usually called the main thread of your program, because it is the one that is executed when your program begins.

It can be controlled through a **Thread** object. You must obtain a reference to it by calling the method *currentThread()*, which is public static member of Thread.

> Static Thread currentThread()

*Example :*

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



## Creating a Thread
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

*Example :*

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
Output
>Main method Thread 5 <br>
Run method Thread 5 <br>
Run method Thread 4 <br>
Run method Thread 3 <br>
Main method Thread 4 <br>
Run method Thread 2 <br>
Main method Thread 3 <br>
Run method Thread 1 <br>
Exiting Run method Thread. <br>
Main method Thread 2 <br>
Main method Thread 1 <br>
Exiting Main Thread <br>
---

### Extending Thread
The second way to create a trade is to create a new class that extends **Thread** and then to create an instance of that class. The extending class must override the run() method.

```java
public class NewThreadExtend extends Thread {

    public void run(){
        for (int i = 5; i >0; i--) {
            System.out.println(Thread.currentThread().getName()+ i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
```


```java
public class ThreadExtendDemo {

    public static void main(String[] args) {
        NewThreadExtend newThreadExtend = new NewThreadExtend();
        newThreadExtend.setName("Run method Thread");
        newThreadExtend.start();

        for (int i = 5; i >0; i--) {
            System.out.println(Thread.currentThread().getName()+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```
---
### Creating multiple Threads

So far, you have been using only two threads: the main thread and one child thread. However, your program can spawn as many threads as it needs. For example the following program creates three child threads.

```java
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
```


```java
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
```


Output: *[ Note - Since you're doing multithreading, there is no guarantee what order threads will run without giving a thread a higher priority over another.]*

>New Thread: Thread[one ,5,main] <br>
New Thread: Thread[Two ,5,main] <br>
New Thread: Thread[Three ,5,main] <br>
one 5 <br>
Three 5 <br>
Two 5 <br>
one 4 <br>
Two 4 <br>
Three 4 <br>
one 3 <br>
Two 3 <br>
Three 3 <br>
one 2 <br>
Two 2 <br>
Three 2 <br>
one 1 <br>
Two 1 <br>
Three 1 <br>
one Exiting... <br>
Two Exiting... <br>
Three Exiting... <br>
Main Thread Exiting... <br>
---

### Thread Priorities
Thread priorities are used by the thread scheduler to decide when each thread should be allowed to run. In theory, over a given period of time, higher priority threads get more CPU time than lower priority threads. In practice, the amount of CPU time that a thread gets often depends on several factors besides its priority. A higher priority thread can also preempt the lower priority thread.

In theory, threads of equal priority should get equal access to the CPU. But you need to be careful. Remember Java is designed to work in a wide range of environments. some of the environments implement multitasking fundamentally differently than others.

To set a thread’s priority, use the setPriority() method, which is a member of **Thread**.
> Final void setPriority(int level)

Here, Level specifies the new priority setting for the calling thread. The value of the level must be within the range MIN_PRIORITY and MAX_PRIORITY. Currently, these values are 1 and 10 respectively. To return a thread to default priority, specify NORM_PRIORITY, which is currently 5. These priorities are defined as static final variable within **Thread**.

You can obtain the current priority setting by calling the getPriority() method of **Thread**.
> Final int getPriority()

Implementation of Java may have radically different behavior when it comes to scheduling. Most of the inconsistencies arise when you have threads that are relying on preemptive behavior, instead of cooperatively giving up CPU time. the safest way to obtain predictable, cross-platform behavior with Java is to use threads that voluntarily give up control of the CPU.





