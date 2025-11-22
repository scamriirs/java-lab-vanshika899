import java.util.Scanner; 
 
class Palindrome { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        int n = sc.nextInt(); 
        int temp = n; 
        int rev = 0; 
        while (temp > 0) { 
            rev = rev * 10 + temp % 10; 
            temp /= 10; 
        } 
        if (rev == n) 
            System.out.println("Palindrome"); 
        else 
            System.out.println("Not Palindrome"); 
    } 
} 