import java.awt.*; 
import java.awt.event.*; 
 
public class AWTLabelButtonDemo extends Frame implements ActionListener { 
    Label lbl; 
    Button btnInc, btnReset, btnExit; 
    int count = 0; 
 
    public AWTLabelButtonDemo() { 
        setTitle("AWT Label and Button Demo"); 
        setSize(350, 150); 
        setLayout(new FlowLayout()); 
        lbl = new Label("Count: 0"); 
        btnInc = new Button("Increment"); 
        btnReset = new Button("Reset"); 
        btnExit = new Button("Exit"); 
        add(lbl); 
        add(btnInc); 
        add(btnReset); 
        add(btnExit); 
        btnInc.addActionListener(this); 
        btnReset.addActionListener(this); 
        btnExit.addActionListener(this); 
        addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent we) { 
                dispose(); 
                System.exit(0); 
            } 
        }); 
        setVisible(true); 
    } 
 
    public void actionPerformed(ActionEvent ae) { 
        Object src = ae.getSource(); 
        if (src == btnInc) { 
            count++; 
            lbl.setText("Count: " + count); 
        } else if (src == btnReset) { 
            count = 0; 
            lbl.setText("Count: " + count); 
        } else if (src == btnExit) { 
            dispose(); 
            System.exit(0); 
        } 
    } 
 
    public static void main(String[] args) { 
        new AWTLabelButtonDemo(); 
    } 
}