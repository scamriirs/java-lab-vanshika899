import java.util.Scanner; 
 
class MatrixSumSub { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        int r = sc.nextInt(); 
        int c = sc.nextInt(); 
        int[][] a = new int[r][c]; 
        int[][] b = new int[r][c]; 
        int[][] sum = new int[r][c]; 
        int[][] sub = new int[r][c]; 
        for (int i = 0; i < r; i++) 
            for (int j = 0; j < c; j++) 
                a[i][j] = sc.nextInt(); 
        for (int i = 0; i < r; i++) 
            for (int j = 0; j < c; j++) 
                b[i][j] = sc.nextInt(); 
        for (int i = 0; i < r; i++) 
            for (int j = 0; j < c; j++) { 
                sum[i][j] = a[i][j] + b[i][j]; 
                sub[i][j] = a[i][j] - b[i][j]; 
            } 
        for (int i = 0; i < r; i++) { 
            for (int j = 0; j < c; j++) 
                System.out.print(sum[i][j] + " "); 
            System.out.println(); 
        } 
        for (int i = 0; i < r; i++) { 
            for (int j = 0; j < c; j++) 
                System.out.print(sub[i][j] + " "); 
            System.out.println(); 
        } 
    } 
} 