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

