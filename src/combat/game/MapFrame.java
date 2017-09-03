package combat.game;

import java.awt.*;
import javax.swing.*;

public class MapFrame extends JFrame {
    JTextArea mapArea = new JTextArea();
    public MapFrame () {
        super("Adventure Map");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout layout = new GridLayout(1, 1);
        setLayout(layout);
        //setLookAndFeel();
        add(mapArea);
        setVisible(true);
    }
    void displayRoom (Room r) {
        mapArea.setText("");
        for (int i = 0; i < r.roomDisplay.size(); i++) {
            mapArea.append(r.roomDisplay.get(i));
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