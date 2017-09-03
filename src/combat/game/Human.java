package combat.game;

public class Human extends NPC {

    
    public Human () {
        
        super();
        hitPointDie = 8;
        numberOfHitPointDice = 2;
        hitPointModifier = 6;
        
        challenge = .5;
        XP = 0;
        MaxHealth = healthPoints;
        attackDamage = 2;
        armorClass = 13;
        speed = 30;
        initiative = 1;
        proficiencyBonus = 2;
    
    //ability scores
        strength = 16;
        dexterity = 12;
        constitution = 16;
        intelligence = 7;
        wisdom = 11;
        charisma = 10;
        title = "Human";
        String name = new String();
        isPlayer = false;
        faction = 1;
        hostile = false;
        alive = true;
        icon = "H";
       
        
        
    } 

    @Override
    public void add(Room r, Cell c, NPC npc) {
        
        npc.rollNumbers();
        npc.title = npc.title + (Integer.toString(r.roomContains.size() + 1));
        npc.cellAddress = c;
        npc.charPoint.setLocation(c.cellCord);
        npc.roomAddress = r;
        c.cellContains.add(npc);
        r.roomContains.add(npc);
        r.roomNPCs.add(npc);
    }
}