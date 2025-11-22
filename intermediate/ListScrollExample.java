import java.awt.*; 
import java.awt.event.*; 
 
public class ListScrollExample extends Frame implements AdjustmentListener, ItemListener 
{ 
 
    List itemList; 
    Scrollbar scrollbar; 
    Label listLabel, scrollLabel; 
 
    public ListScrollExample() { 
        setLayout(new FlowLayout()); 
 
        itemList = new List(5, false); 
        itemList.add("Java"); 
        itemList.add("Python"); 
        itemList.add("C++"); 
        itemList.add("JavaScript"); 
        itemList.add("Kotlin"); 
 
        listLabel = new Label("Selected Item: "); 
 
        scrollbar = new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, 100); 
        scrollLabel = new Label("Scroll Value: 0"); 
 
        add(itemList); 
        add(scrollbar); 
        add(listLabel); 
        add(scrollLabel); 
 
        itemList.addItemListener(this); 
        scrollbar.addAdjustmentListener(this); 
 
        setSize(350, 250); 
        setTitle("AWT List & Scrollbar Example"); 
        setVisible(true); 
    } 
 
    public void itemStateChanged(ItemEvent e) { 
        listLabel.setText("Selected Item: " + itemList.getSelectedItem()); 
    } 
 
    public void adjustmentValueChanged(AdjustmentEvent e) { 
        scrollLabel.setText("Scroll Value: " + scrollbar.getValue()); 
    } 
 
    public static void main(String[] args) { 
        new ListScrollExample(); 
    } 
}