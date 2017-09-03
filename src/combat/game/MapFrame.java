package combat.game;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class MapFrame extends JFrame {
    ArrayList<String> scroll = new ArrayList();
    JPanel row1 = new JPanel();
    JLabel infoBar = new JLabel("Info Bar");
    JLabel actionBar = new JLabel("Action Bar");
    
    JPanel row2 = new JPanel();
    JTextArea mapArea = new JTextArea();
    

    JTextArea actionArea = new JTextArea();    
    

    DefaultCaret caret = (DefaultCaret)actionArea.getCaret();

    
    JScrollPane scrollPanel = new JScrollPane(actionArea);
    

    public MapFrame () {
        super("Adventure Map");
        
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        scrollPanel.add(actionArea);
        scrollPanel.setViewportView(actionArea);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BoxLayout row1Layout = new BoxLayout(row1, BoxLayout.Y_AXIS);
        row1.setLayout(row1Layout);
        row1.add(infoBar);
        row1.add(actionBar);
        
        GridLayout row2Layout = new GridLayout(1,2);
        row2.setLayout(row2Layout);

        row2.add(scrollPanel);
        row2.add(mapArea);
        
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        //setLookAndFeel();
        add(row1, BorderLayout.NORTH);
        add(row2, BorderLayout.CENTER);
        setVisible(true);
    }
    void displayRoom (Room r) {
        mapArea.setText("");
        for (int i = 0; i < r.roomDisplay.size(); i++) {
            mapArea.append(r.roomDisplay.get(i));
        }
    }
    void displayInfo(String info) {
        infoBar.setText(info);
    }
    void displayActions(String actions) {
        actionBar.setText(actions);
    }
    void tickerTape (String s) {

        scroll.add(s);
        actionArea.setText("");
        for (int i = 0; i < scroll.size(); i++) {
            actionArea.append(scroll.get(i));
        }
    }

       private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
            "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
            );
        } catch (Exception exc) {
            // ignore error
        }
    }
}