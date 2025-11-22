import java.util.Scanner; 
 
class Customer { 
    String name; 
    int units; 
    Customer(String n, int u) { 
        name = n; 
        units = u; 
    } 
} 
 
class Bill extends Customer { 
    double amount; 
    Bill(String n, int u) { 
        super(n, u); 
    } 
    void calculate() { 
        if (units <= 100) amount = units * 5; 
        else if (units <= 200) amount = 100 * 5 + (units - 100) * 7; 
        else amount = 100 * 5 + 100 * 7 + (units - 200) * 10; 
    } 
    void display() { 
        System.out.println(name); 
        System.out.println(units); 
        System.out.println(amount); 
    } 
} 
 
class Main { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        String n = sc.nextLine(); 
        int u = sc.nextInt(); 
        Bill b = new Bill(n, u); 
        b.calculate(); 
        b.display(); 
    } 
}