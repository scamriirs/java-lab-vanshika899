import java.util.Scanner; 
 
class DecToBinary { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        int n = sc.nextInt(); 
        if (n == 0) { 
            System.out.println(0); 
            return; 
        } 
        boolean neg = n < 0; 
        long num = n; 
        if (neg) num = -num; 
        int[] stack = new int[64]; 
        int top = 0; 
        while (num > 0) { 
            stack[top++] = (int)(num % 2); 
            num /= 2; 
        } 
        StringBuilder sb = new StringBuilder(); 
        if (neg) sb.append('-'); 
        for (int i = top - 1; i >= 0; i--) sb.append(stack[i]); 
        System.out.println(sb.toString()); 
    } 
} 
