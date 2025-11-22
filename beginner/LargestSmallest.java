import java.util.Scanner; 
 
class LargestSmallest { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        int n = sc.nextInt(); 
        int first = sc.nextInt(); 
        int max = first; 
        int min = first; 
        for (int i = 2; i <= n; i++) { 
            int x = sc.nextInt(); 
            if (x > max) max = x; 
            if (x < min) min = x; 
        } 
        System.out.println(max); 
        System.out.println(min); 
    } 
}