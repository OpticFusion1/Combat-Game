package combat.game;

import java.util.ArrayList;
import java.awt.*;
   

public class Cell {
    
    public Point cellCord = new Point();
    Room parentRoom;
    ArrayList<Character> cellContains = new ArrayList();
    String icon = ".";
    Structure cellStructure;
    boolean occupied;
    boolean pathable;
    
    public Cell(Room r, int x, int y) {
        parentRoom = r;
        cellCord.setLocation(x,y);
        FloorTile t = new FloorTile();
        cellStructure = t;
    }
    void updateIcon() {
        if (cellContains.size() < 1) {
          icon  = cellStructure.icon;    
        }
        if (cellContains.size() == 1) {
           icon = cellContains.get(0).icon;
        }
        if (cellContains.size() > 1) {
        for (int i = 0; i < cellContains.size(); i++) {
            icon = cellContains.get(0).icon;
            if (cellContains.get(i).alive == true) {
                icon = cellContains.get(i).icon;
            }
        }
        }
        
    }
    void cellAddressCountOff() {
        for (int i=0; i<cellContains.size();i++) {
            cellContains.get(i).spotInArray = i;
        }
    }
    public boolean cellOccupied() {
        if (cellContains.size() >= 1) {
            return true;
        }
        else {
            return false;
        }
    }
}

