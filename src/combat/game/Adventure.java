package combat.game;

public class Adventure {
    MapFrame mapFrame = new MapFrame();
    Map map = new Map();
    Player player1 = new Player();
    CombatGame combatGame = new CombatGame();
    ExplorationGame eg = new ExplorationGame();
    

    public static void main(String[] args) throws InterruptedException {
        Adventure a = new Adventure();
        a.map.createRedKeep(a.mapFrame);
        a.startAdventure(a.map, a.player1);
}
    void addPlayerToRoom(Player p, Room r) {
        player1.addPlayer(p, r, r.roomTiles[1][1]);
    }
    void startAdventure (Map map, Player player1) throws InterruptedException {
        //  Room one starts a little bit differantly.  
        // It is outside the for loop that does the rest of the rooms.
        addPlayerToRoom (player1, map.redKeep.get(0));
        if (player1.alive == true) {
            player1.addPlayerToNewRoom (player1, map.redKeep.get(0), map.redKeep.get(0).roomTiles[1][1]);
            combatGame.CombatGame(map.redKeep.get(0), player1);
        }
        if (player1.alive == true) {
            eg.ExplorationGame(map.redKeep.get(0), player1); 
        }
        //This for loops runs the rest of the rooms, starting with the 2nd room,
        // That is why int i starts at 1 instead of 0.  
        //The final room must also be seperate, so that the game ends.
        for ( int i = 1; i < map.redKeep.size()-1; i++) {
          if (player1.alive == true) {
            player1.addPlayerToNewRoom (player1, map.redKeep.get(i), map.redKeep.get(i).roomTiles[1][1]);
            combatGame.CombatGame(map.redKeep.get(i), player1);
        }
        if (player1.alive == true) {
           eg.ExplorationGame(map.redKeep.get(i), player1); 
        }  
        }
        if (player1.alive == true) {
            Room finalRoom = map.redKeep.get(map.redKeep.size());
            player1.addPlayerToNewRoom (player1, finalRoom, finalRoom.roomTiles[1][1]);
            combatGame.CombatGame(finalRoom, player1);
        }
    }
}
