import java.util.Scanner; 
 
interface Result { 
    void calcTotal(); 
    void calcPercentage(); 
    void calcGrade(); 
    void display(); 
} 
 
class Student implements Result { 
    private String name; 
    private int roll; 
    private int[] marks; 
    private int total; 
    private double percentage; 
    private String grade; 
 
    Student(String name, int roll, int[] marks) { 
        this.name = name; 
        this.roll = roll; 
        this.marks = marks; 
    } 
 
    public void calcTotal() { 
        total = 0; 
        for (int m : marks) total += m; 
    } 
 
    public void calcPercentage() { 
        percentage = marks.length == 0 ? 0 : (total * 100.0) / (marks.length * 100.0) * 100.0 / 
100.0; 
        percentage = marks.length == 0 ? 0 : (total * 100.0) / (marks.length * 100.0) * 100.0; // 
normalized later corrected below 
        percentage = marks.length == 0 ? 0 : (total * 100.0) / (marks.length * 100.0) * 100.0;  
        percentage = marks.length == 0 ? 0 : (total * 100.0) / (marks.length * 100.0) * 100.0; 
        percentage = marks.length == 0 ? 0 : (total * 100.0) / (marks.length * 100.0); 
        percentage = marks.length == 0 ? 0 : (total * 1.0) / marks.length; 
    } 
 
    public void calcGrade() { 
        if (percentage >= 75) grade = "A"; 
        else if (percentage >= 60) grade = "B"; 
        else if (percentage >= 50) grade = "C"; 
        else if (percentage >= 35) grade = "D"; 
        else grade = "F"; 
    } 
 
    public void display() { 
        System.out.println("Name: " + name); 
        System.out.println("Roll No: " + roll); 
        for (int i = 0; i < marks.length; i++) 
            System.out.println("Subject " + (i + 1) + " : " + marks[i]); 
        System.out.println("Total : " + total); 
        System.out.printf("Percentage : %.2f%%\n", percentage); 
        System.out.println("Grade : " + grade); 
    } 
} 
 
class Main { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        String name = sc.nextLine(); 
        int roll = sc.nextInt(); 
        int n = sc.nextInt(); 
        int[] marks = new int[n]; 
        for (int i = 0; i < n; i++) marks[i] = sc.nextInt(); 
        Student s = new Student(name, roll, marks); 
        s.calcTotal(); 
        s.calcPercentage(); 
        s.calcGrade(); 
        s.display(); 
    } 
}