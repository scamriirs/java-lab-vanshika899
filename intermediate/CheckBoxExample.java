import java.awt.*; 
import java.awt.event.*; 
 
public class CheckBoxExample extends Frame implements ItemListener { 
 
    Checkbox cb1, cb2, cb3; 
    Label result; 
 
    public CheckBoxExample() { 
 
        setLayout(new FlowLayout()); 
 
        cb1 = new Checkbox("Java"); 
        cb2 = new Checkbox("Python"); 
        cb3 = new Checkbox("C++"); 
 
        result = new Label("Selected: "); 
 
        add(cb1); 
        add(cb2); 
        add(cb3); 
        add(result); 
 
        cb1.addItemListener(this); 
        cb2.addItemListener(this); 
        cb3.addItemListener(this); 
 
        setSize(300, 200); 
        setTitle("AWT CheckBox Example"); 
        setVisible(true); 
    } 
 
    public void itemStateChanged(ItemEvent e) { 
        String selected = "Selected: "; 
 
        if(cb1.getState()) 
            selected += "Java "; 
        if(cb2.getState()) 
            selected += "Python "; 
        if(cb3.getState()) 
            selected += "C++ "; 
 
        result.setText(selected); 
    } 
 
    public static void main(String[] args) { 
        new CheckBoxExample(); 
    } 
}