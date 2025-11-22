class MyThread implements Runnable { 
    public void run() { 
        for (int i = 1; i <= 5; i++) 
            System.out.println(Thread.currentThread().getName() + " : " + i); 
    } 
} 
 
class Main { 
    public static void main(String[] args) { 
        Thread t1 = new Thread(new MyThread(), "Thread 1"); 
        Thread t2 = new Thread(new MyThread(), "Thread 2"); 
        t1.start(); 
        t2.start(); 
    } 
}