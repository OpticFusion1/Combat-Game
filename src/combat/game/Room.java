package combat.game;

import java.util.ArrayList;

public class Room {
    
    Cell[][] roomTiles;
    ArrayList<Character> roomContains = new ArrayList();
    ArrayList<NPC> roomNPCs = new ArrayList();
    ArrayList<String> roomDisplay = new ArrayList();
    int height;
    int encounterXP = 0;
    int width;
    MapFrame outPut;
    
    public Room (int rows, int cols) {
        roomTiles = new Cell[rows][cols];
        height = rows;
        width = cols;
}
    void buildRoom() {
        
        for (int row = 0 ; row < height; row++) {
            for (int col = 0 ; col < width; col++){
               Cell cell = new Cell(this, col, row);
               
               //Outer tiles contain walls.  All others contain tiles.
               if (row == 0 ||
                       row == height -1 ||
                       col == 0 ||
                       col == width -1) {
                   Wall w = new Wall();
                   cell.cellStructure = w;
               }
               roomTiles[row][col] = cell; 
            }
         }
        /*for (int r = 0; r < height; r++) {
        Wall wall = new Wall();
        roomTiles[0][0].cellStructure = wall;
        //roomTiles[0][height].cellStructure = wall;
        }
        for (int c = 0; c < width; c++) {
        Wall wall = new Wall();
        //roomTiles[c][0].cellStructure = wall;
        //roomTiles[c][width].cellStructure = wall;
        }*/
        
    }
        void drawRoom() {
            outPut.mapArea.setText("");
            roomDisplay.clear();
        for (int row = 0 ; row < height; row++) {
            for (int col = 0 ; col < width; col++){
                roomTiles[row][col].updateIcon();
                roomDisplay.add(roomTiles[row][col].icon + " ");
            }
            
             roomDisplay.add("\n");
        }
        outPut.displayRoom(this);
        }
        
}
        

