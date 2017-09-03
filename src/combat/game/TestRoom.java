package combat.game;

import java.awt.Point;

public class TestRoom{
    
    CombatGame combatGame = new CombatGame();
    Ticker tick = new Ticker();
   
    
    

    public static void main(String[] args) throws InterruptedException {
        TestRoom t = new TestRoom();
        System.out.println("Hello World");
        t.talk();
        MapFrame mapFrame = new MapFrame();
        
        

        
        Room room1 = new Room(5, 5);
        room1.buildRoom();
        Orc orc = new Orc();
        orc.charPoint.setLocation(1, 1);
        room1.roomTiles[1][1].cellContains.add(orc);
        room1.drawRoom();
        
        
        Point p = new Point();
        p.setLocation(1, 1);
        System.out.println(p.toString());
        mapFrame.displayRoom(room1);
        
        
    } 
    void talk() throws InterruptedException {
        for (int i =0; i < 3; i++) {
           tick.add("Hello Ticker");  
        }
       
    }
}
   