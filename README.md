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

### isAlive() and join()

The isAlive() method of thread class tests if the thread is alive. A thread is considered alive when the start() method of thread class has been called and the thread is not yet dead. This method returns true if the thread is still running and not finished.

>	public final boolean isAlive()  

The join() method of thread class waits for a thread to die. It is used when you want one thread to wait for completion of another. This process is like a relay race where the second runner waits until the first runner comes and hand over the flag to him.

>	public final void join()throws InterruptedException  
>	public void join(long millis)throwsInterruptedException  
>	public final void join(long millis, int nanos)throws InterruptedException

*Example :*

```java
public class ThreadJoinandIsAlive {
    
    public static void main(String[] args) {
        NewThreadWithConstructor nw1 = new NewThreadWithConstructor("One ");
        NewThreadWithConstructor nw2 = new NewThreadWithConstructor("Two ");
        NewThreadWithConstructor nw3 = new NewThreadWithConstructor("Three ");

        nw1.t.start();
        try {
            
            nw1.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nw2.t.start();
        nw3.t.start();

        System.out.println("Thread one is Alive "+ nw1.t.isAlive());
        System.out.println("Thread two is Alive "+ nw2.t.isAlive());
        System.out.println("Thread three is Alive "+ nw3.t.isAlive());
    }
}
```

Output:
>New Thread: Thread[One ,5,main] <br>
New Thread: Thread[Two ,5,main] <br>
New Thread: Thread[Three ,5,main] <br>
One 5 <br>
One 4 <br>
One 3 <br>
One 2 <br>
One 1 <br>
One Exiting... <br>
Thread one is Alive false <br>
Thread two is Alive true  <br>
Thread three is Alive true <br>
Three 5 <br>
Two 5 <br>
Three 4 <br>
Two 4 <br>
Three 3 <br>
Two 3 <br>
Three 2 <br>
Two 2 <br>
Three 1 <br>
Two 1 <br>
Three Exiting... <br>
Two Exiting... <br>


### Thread Priorities
Thread priorities are used by the thread scheduler to decide when each thread should be allowed to run. In theory, over a given period of time, higher priority threads get more CPU time than lower priority threads. In practice, the amount of CPU time that a thread gets often depends on several factors besides its priority. A higher priority thread can also preempt the lower priority thread.

In theory, threads of equal priority should get equal access to the CPU. But you need to be careful. Remember Java is designed to work in a wide range of environments. some of the environments implement multitasking fundamentally differently than others.

To set a thread’s priority, use the setPriority() method, which is a member of **Thread**.
> Final void setPriority(int level)

Here, Level specifies the new priority setting for the calling thread. The value of the level must be within the range MIN_PRIORITY and MAX_PRIORITY. Currently, these values are 1 and 10 respectively. To return a thread to default priority, specify NORM_PRIORITY, which is currently 5. These priorities are defined as static final variable within **Thread**.

You can obtain the current priority setting by calling the getPriority() method of **Thread**.
> Final int getPriority()

Implementation of Java may have radically different behavior when it comes to scheduling. Most of the inconsistencies arise when you have threads that are relying on preemptive behavior, instead of cooperatively giving up CPU time. the safest way to obtain predictable, cross-platform behavior with Java is to use threads that voluntarily give up control of the CPU.

---

### Synchronization

When two or more threads need access to shared resource, they need some way to ensure that the resource will be used by only one thread at a time. The process by which this is achieved is called *synchronization*. Java provides unique, language level support for it.

Key to synchronization is the concept of the monitor. A monitor is an object that is used as a mutually exclusive lock. Only one thread can own a monitor at a given time. When a thread acquires a lock, it is said to have entered the monitor. All other threads attempting to enter the locked monitor will be suspended until the first thread exits the monitor. These other threads are said to be waiting for the monitor. 

You can synchronize your code in either two ways, both involve the use of the synchronized keyword

### 1)Using Synchronized Method

Synchronization is easy in Java because all objects have their own implicit monitor associated with them. To enter an object’s monitor, just call a method that has been modified with the synchronized keyword. While a thread is inside a synchronized method, all other threads that try to call it on the same instance have to wait. To exit the monitor and resume control of the object to the next waiting thread, the owner of the monitor simply returns from the synchronized method.







