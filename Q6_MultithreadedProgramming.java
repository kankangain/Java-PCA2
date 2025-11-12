// Question 6: Multithreaded Programming in Java

class MyThread extends Thread {
    private String threadName;
    
    public MyThread(String name) {
        this.threadName = name;
    }
    
    @Override
    public void run() {
        System.out.println(threadName + " started");
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + " - Count: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted");
            }
        }
        System.out.println(threadName + " finished");
    }
}

class MyRunnable implements Runnable {
    private String taskName;
    
    public MyRunnable(String name) {
        this.taskName = name;
    }
    
    @Override
    public void run() {
        System.out.println(taskName + " started");
        for (int i = 1; i <= 5; i++) {
            System.out.println(taskName + " - Processing: " + i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println(taskName + " interrupted");
            }
        }
        System.out.println(taskName + " finished");
    }
}

class BankAccount {
    private int balance = 1000;
    
    public synchronized void withdraw(String user, int amount) {
        System.out.println(user + " attempting to withdraw $" + amount);
        if (balance >= amount) {
            System.out.println(user + " - Current balance: $" + balance);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println(user + " - Withdrawal successful! New balance: $" + balance);
        } else {
            System.out.println(user + " - Insufficient funds! Balance: $" + balance);
        }
    }
    
    public int getBalance() {
        return balance;
    }
}

class PriorityThread extends Thread {
    public PriorityThread(String name) {
        super(name);
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(getName() + " (Priority: " + getPriority() + ") - Count: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SharedResource {
    private int data;
    private boolean hasData = false;
    
    public synchronized void produce(int value) throws InterruptedException {
        while (hasData) {
            wait();
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + data);
        notify();
    }
    
    public synchronized int consume() throws InterruptedException {
        while (!hasData) {
            wait();
        }
        hasData = false;
        System.out.println("Consumed: " + data);
        notify();
        return data;
    }
}

class Producer implements Runnable {
    private SharedResource resource;
    
    public Producer(SharedResource resource) {
        this.resource = resource;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                resource.produce(i * 10);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private SharedResource resource;
    
    public Consumer(SharedResource resource) {
        this.resource = resource;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                resource.consume();
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Q6_MultithreadedProgramming {
    
    public static void basicThreadExample() {
        System.out.println("=== Basic Thread Example ===");
        
        MyThread thread1 = new MyThread("Thread-1");
        MyThread thread2 = new MyThread("Thread-2");
        
        thread1.start();
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
    
    public static void runnableExample() {
        System.out.println("=== Runnable Interface Example ===");
        
        Thread t1 = new Thread(new MyRunnable("Task-A"));
        Thread t2 = new Thread(new MyRunnable("Task-B"));
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
    
    public static void synchronizationExample() {
        System.out.println("=== Thread Synchronization Example ===");
        
        BankAccount account = new BankAccount();
        
        Thread user1 = new Thread(() -> {
            account.withdraw("User1", 600);
        });
        
        Thread user2 = new Thread(() -> {
            account.withdraw("User2", 700);
        });
        
        user1.start();
        user2.start();
        
        try {
            user1.join();
            user2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Final balance: $" + account.getBalance());
        System.out.println();
    }
    
    public static void threadPriorityExample() {
        System.out.println("=== Thread Priority Example ===");
        
        PriorityThread highPriority = new PriorityThread("HighPriority");
        PriorityThread normalPriority = new PriorityThread("NormalPriority");
        PriorityThread lowPriority = new PriorityThread("LowPriority");
        
        highPriority.setPriority(Thread.MAX_PRIORITY);
        normalPriority.setPriority(Thread.NORM_PRIORITY);
        lowPriority.setPriority(Thread.MIN_PRIORITY);
        
        highPriority.start();
        normalPriority.start();
        lowPriority.start();
        
        try {
            highPriority.join();
            normalPriority.join();
            lowPriority.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
    
    public static void producerConsumerExample() {
        System.out.println("=== Producer-Consumer Example ===");
        
        SharedResource resource = new SharedResource();
        
        Thread producerThread = new Thread(new Producer(resource));
        Thread consumerThread = new Thread(new Consumer(resource));
        
        producerThread.start();
        consumerThread.start();
        
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
    
    public static void threadLifecycleExample() {
        System.out.println("=== Thread Lifecycle Example ===");
        
        Thread thread = new Thread(() -> {
            System.out.println("Thread is running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread completed!");
        });
        
        System.out.println("State: " + thread.getState());
        
        thread.start();
        System.out.println("State: " + thread.getState());
        
        try {
            Thread.sleep(100);
            System.out.println("State: " + thread.getState());
            thread.join();
            System.out.println("State: " + thread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Java Multithreading Demo ===\n");
        System.out.println("Main thread: " + Thread.currentThread().getName());
        System.out.println();
        
        basicThreadExample();
        
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        runnableExample();
        
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        synchronizationExample();
        
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        threadPriorityExample();
        
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        producerConsumerExample();
        
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        threadLifecycleExample();
        
        System.out.println("All multithreading examples completed!");
    }
}
