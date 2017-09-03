package combat.game;

import java.util.ArrayList;

public class Map {
    
    ArrayList<Room> redKeep = new ArrayList();
    
    Dice dice = new Dice();
    
    public Map () {
        
        
        
        
        
        

        //addOrcWarChief(room6, room6.roomTiles[5][12]);
        
    }
    public void createRedKeep (MapFrame frame) {
        
        
        
        addRoom(5 ,5, frame);
        addGoblinAnywhereInRoom(redKeep.get(0), 1);
        Human h = new Human();
        h.name = "Thomas";
        h.talkText = " Hello, my name is Thomas.";
        h.addAnywhereInRoom(redKeep.get(0), 1, h);
        
        addRoom(4 ,6, frame);
        addGoblinAnywhereInRoom(redKeep.get(1), 1);
        
        addRoom(5 ,7, frame);
        addGoblinAnywhereInRoom(redKeep.get(2), 1);
        
        addRoom(4 ,10, frame);
        addGoblinAnywhereInRoom(redKeep.get(3), 2);
        
        addRoom(6 ,6, frame);
        addOrcAnywhereInRoom(redKeep.get(4), 1);
        
        addRoom(7 ,10, frame);
        addGoblinAnywhereInRoom(redKeep.get(5), 1);
        addOrcAnywhereInRoom(redKeep.get(5), 1);
        
        addRoom(10 ,10, frame);
        addOrcAnywhereInRoom(redKeep.get(6), 2);
        
        addRoom(12 ,10, frame);
        addGoblinAnywhereInRoom(redKeep.get(7), 4);
        
        addRoom(4 ,16, frame);
        addGoblinAnywhereInRoom(redKeep.get(8), 1);
        addOrcAnywhereInRoom(redKeep.get(8), 2);
        
        addRoom(12 ,20, frame);
        addGoblinAnywhereInRoom(redKeep.get(9), 5);
        
        addRoom(20, 25, frame);
        //addGoblinBoss(redKeep.get(10), redKeep.get(10).roomTiles[4][4]);
        addGoblinAnywhereInRoom(redKeep.get(10), 6);
        
    }
    void addOrcWarChief (Room r, Cell c) {

        OrcWarChief orc = new OrcWarChief();
        orc.add(r, c, orc);

        
        }
    void addOrc (Room r, Cell c) {
        Orc orc = new Orc();
        orc.add(r,c,orc);

    }
    void addOrcAnywhereInRoom (Room r, int orcs) {
        for (int i = 0; i < orcs; i++) {
        addOrc(r, r.roomTiles[dice.rollDice(r.height-2)][dice.rollDice(r.width-2)]);
        }
        
    }
    void addGoblin (Room r, Cell c) {
        Goblin npc = new Goblin();
        npc.add(r, c, npc);
    }
    void addGoblinAnywhereInRoom (Room r, int npc) {
        for (int i = 0; i < npc; i++) {
        addGoblin(r, r.roomTiles[dice.rollDice(r.height-2)][dice.rollDice(r.width-2)]);
        }
}
    void addRoom(int columns, int rows, MapFrame frame) {
        Room room = new Room(columns, rows);
        room.buildRoom();
        room.outPut = frame;
        redKeep.add(room);
        
    }
    void addGoblinBoss (Room r, Cell c) {

        GoblinBoss npc = new GoblinBoss();
        npc.add(r,c,npc);

    }
    void addHuman (Room r, Cell c) {
        Human npc = new Human();
        npc.add(r, c, npc);
    }
    
}