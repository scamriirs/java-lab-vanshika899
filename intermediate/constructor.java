class constructor { 
    int a; 
    constructor() { 
        a = 10; 
    } 
    constructor(int x) { 
        a = x; 
    } 
    public static void main(String[] args) { 
        constructor d1 = new constructor(); 
        constructor d2 = new constructor(50); 
        System.out.println(d1.a); 
        System.out.println(d2.a); 
    } 
} 