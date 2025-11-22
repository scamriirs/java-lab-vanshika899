import java.util.Scanner; 
 
abstract class Shape { 
    abstract double area(); 
} 
 
class Triangle extends Shape { 
    double b, h; 
    Triangle(double b, double h) { 
        this.b = b; 
        this.h = h; 
    } 
    double area() { 
        return 0.5 * b * h; 
    } 
} 
 
class Rectangle extends Shape { 
    double l, w; 
    Rectangle(double l, double w) { 
        this.l = l; 
        this.w = w; 
    } 
    double area() { 
        return l * w; 
    } 
} 
 
class Circle extends Shape { 
    double r; 
    Circle(double r) { 
        this.r = r; 
    } 
    double area() { 
        return Math.PI * r * r; 
    } 
} 
 
class Main { 
    public static void main(String[] args) { 
        Triangle t = new Triangle(10, 5); 
        Rectangle r = new Rectangle(8, 4); 
        Circle c = new Circle(6); 
        System.out.println(t.area()); 
        System.out.println(r.area()); 
        System.out.println(c.area()); 
    } 
}