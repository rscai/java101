# Concurrency

## Processes and Threads

### Thread Lifecycle

![Alt text](http://g.gravizo.com/g?
@startuml;
[*] --> New;
New --> Runnable : start;
Runnable --> Terminated : terminated;
Runnable --> TimedWaiting : sleep(sleeptime);
Runnable --> TimedWaiting : wait(timeout);
Runnable --> TimedWaiting : join(timeout);
Runnable --> TimedWaiting : LockSupport.parkNanos();
Runnable --> TimedWaiting : LockSupport.parkUntil();
TimedWaiting --> Runnable : timeout elapsed;
TimeWaiting --> Terminated : thread terminated;
Runnable --> Waiting : wait;
Runnable --> Waiting : join;
Runnable --> Waiting : LockSupport.park;
Waiting --> Runnable : notify;
Waiting --> Runnable : notifyAll;
Waiting --> Terminated : thread terminated;
Runnable --> Blocked : wait for lock to enter synchro block or method;
Runnable --> Blocked : wait for lock to reenter synchro block or method;
Blocked --> Runnable : monitor lock acquired;
Terminated --> [*];
@enduml
)

![Alt text](http://g.gravizo.com/g?
@startuml;
title state Runnable
[*] --> Ready;
Ready --> Running : thread was selected by thread scheduler to run;
Running --> Ready : yield;
Running --> Ready : thread was suspended by thread scheduler;
Running --> [*];
@enduml
)

## Synchronization

1. **Thread Interference** describes how errors are introduced when multiple threads access shared data.
2. **Memory Consistency Errors** describes errors that result from inconsistent views of shared memory.
3. **Synchronized Methods** describes a simple idiom that can effectively prevent thread interference and memory consistency errors.
4. **Implicit Locks** and Synchronization describes a more general synchronization idiom, and describes how synchronization is based on implicit locks.
5. **Atomic Access** talks about the general idea of operations that can't be interfered with by other threads.

### Thread Interference

--TBD--

### Memory Consistency Errors

TODO: 

1. **happens-before** relationship, refers to Java Memory Model

## Synchronized Methods

1. **synchronized methods**
2. **synchronized statements**

## High Level Concurrency

1. Lock Objects
2. Executors
3. Concurrent Collections
4. Atomic Variables
5. Concurrent Random Numbers

### Lock Objects

### Executors

**Executor Interfaces**

The java.util.concurrent package defines three executor interfaces:

* Executor, a simple interface that supports launching new tasks.
* ExecutorService, a subinterface of Executor, which adds features that help manage the lifecycle, both of the individual tasks and of the executor itself.
* ScheduledExecutorService, a subinterface of ExecutorService, supports future and/or periodic execution of tasks.




