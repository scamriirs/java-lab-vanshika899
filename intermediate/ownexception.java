class InvalidAgeException extends Exception { 
    InvalidAgeException(String msg) { 
        super(msg); 
    } 
} 
 
class Main { 
    static void checkAge(int age) throws InvalidAgeException { 
        if (age < 18) 
            throw new InvalidAgeException("Not eligible"); 
        else 
            System.out.println("Eligible"); 
    } 
 
    public static void main(String[] args) { 
        try { 
            checkAge(15); 
        } catch (InvalidAgeException e) { 
            System.out.println(e.getMessage()); 
        } 
    } 
} 
