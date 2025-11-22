class MyThread1 extends Thread { 
    public void run() { 
        while (true) { 
            System.out.println("Thread1"); 
            try { Thread.sleep(2000); } catch (Exception e) {} 
        } 
    } 
} 
 
class MyThread2 extends Thread { 
    public void run() { 
        while (true) { 
            System.out.println("Thread2"); 
            try { Thread.sleep(4000); } catch (Exception e) {} 
        } 
    } 
} 
 
class Main { 
    public static void main(String[] args) { 
        MyThread1 t1 = new MyThread1(); 
        MyThread2 t2 = new MyThread2(); 
        t1.start(); 
        t2.start(); 
    } 
} 