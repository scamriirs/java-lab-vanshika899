import java.util.Scanner; 
 
abstract class Account { 
    int accNo; 
    double balance; 
    Account(int a, double b) { 
        accNo = a; 
        balance = b; 
    } 
    abstract void deposit(double amt); 
    abstract void withdraw(double amt); 
    void checkBalance() { 
        System.out.println(balance); 
    } 
} 
 
class Savings extends Account { 
    double interestRate; 
    Savings(int a, double b, double ir) { 
        super(a, b); 
        interestRate = ir; 
    } 
    void deposit(double amt) { 
        balance += amt; 
    } 
    void withdraw(double amt) { 
        if (amt <= balance) balance -= amt; 
        else System.out.println("Insufficient Balance"); 
    } 
} 
 
class Current extends Account { 
    double overdraftLimit; 
    Current(int a, double b, double ol) { 
        super(a, b); 
        overdraftLimit = ol; 
    } 
    void deposit(double amt) { 
        balance += amt; 
    } 
    void withdraw(double amt) { 
        if (amt <= balance + overdraftLimit) balance -= amt; 
        else System.out.println("Over Limit"); 
    } 
} 
 
class Main { 
    public static void main(String[] args) { 
        Savings s = new Savings(101, 5000, 5); 
        Current c = new Current(102, 3000, 2000); 
        s.deposit(1000); 
        s.withdraw(2000); 
        s.checkBalance(); 
        c.withdraw(4000); 
        c.checkBalance(); 
    } 
} 
