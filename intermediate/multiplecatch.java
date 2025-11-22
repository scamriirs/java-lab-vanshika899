class Main { 
    public static void main(String[] args) { 
        try { 
            int a = 10; 
            int b = Integer.parseInt("abc"); 
            int c = a / b; 
            System.out.println(c); 
        }  
        catch (NumberFormatException e) { 
            System.out.println("Number format error"); 
        }  
        catch (ArithmeticException e) { 
            System.out.println("Arithmetic error"); 
        }  
        catch (Exception e) { 
            System.out.println("Other error"); 
        } 
    } 
} 