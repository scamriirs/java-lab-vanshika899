class Vehicle { 
    String vehicle_type; 
    Vehicle(String vt) { 
        vehicle_type = vt; 
    } 
    void display() { 
        System.out.println(vehicle_type); 
    } 
} 
 
class Car extends Vehicle { 
    String model_type; 
    String company; 
    Car(String vt, String mt, String c) { 
        super(vt); 
        model_type = mt; 
        company = c; 
    } 
    void display() { 
        super.display(); 
        System.out.println(model_type); 
        System.out.println(company); 
    } 
} 
 
class Main { 
    public static void main(String[] args) { 
        Car c = new Car("Four Wheeler", "Sedan", "Honda"); 
        c.display(); 
    } 
} 