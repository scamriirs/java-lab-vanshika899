class Demo { 
    int x; 
    static int count = 0; 
    Demo(int x) { 
        this.x = x; 
        count++; 
    } 
    void show() { 
        System.out.println(this.x); 
        System.out.println(count); 
    } 
    public static void main(String[] args) { 
        Demo d1 = new Demo(10); 
        Demo d2 = new Demo(20); 
        d1.show(); 
        d2.show(); 
    } 
}