package combat.game;

public abstract class NPC extends Character{
    String talkText;
    
    
    public NPC () {
    }
    

    
    public void speak() {
        System.out.println(talkText);
    }
    public void addAnywhereInRoom (Room r, int q, NPC npc) {
        for (int i = 0; i < q; i++) {
        add(r, r.roomTiles[dice.rollDice(r.height-2)][dice.rollDice(r.width-2)], npc);
        }
    }
    public abstract void add (Room r, Cell c, NPC npc); 

}
