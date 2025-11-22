import java.util.Scanner; 
class matrixproduct { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        int r1 = sc.nextInt(); 
        int c1 = sc.nextInt(); 
        int r2 = sc.nextInt(); 
        int c2 = sc.nextInt(); 
        if (c1 != c2) return; 
        int[][] a = new int[r1][c1]; 
        int[][] b = new int[r2][c2]; 
        int[][] p = new int[r1][c2]; 
        for (int i = 0; i < r1; i++) 
            for (int j = 0; j < c1; j++) 
                a[i][j] = sc.nextInt(); 
        for (int i = 0; i < r2; i++) 
            for (int j = 0; j < c2; j++) 
                b[i][j] = sc.nextInt(); 
        for (int i = 0; i < r1; i++) 
            for (int j = 0; j < c2; j++) 
                for (int k = 0; k < c1; k++) 
                    p[i][j] += a[i][k] * b[k][j]; 
        for (int i = 0; i < r1; i++) { 
            for (int j = 0; j < c2; j++) 
                System.out.print(p[i][j] + " "); 
            System.out.println(); 
        } 
    } 
} 
