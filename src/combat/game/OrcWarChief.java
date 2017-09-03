package combat.game;

public class OrcWarChief extends NPC {
    boolean bloodLust;
    
    public OrcWarChief () {
        
        super();
        sight = 60;
        hitPointDie = 8;
        numberOfHitPointDice = 11;
        hitPointModifier = 44;
        
        challenge = 4;
        XP = 2200;
        MaxHealth = healthPoints;
        attackDamage = 15;
        armorClass = 16;
        speed = 30;
        initiative = 1;
        proficiencyBonus = 2;
    
    //ability scores
        strength = 18;
        dexterity = 12;
        constitution = 18;
        intelligence = 11;
        wisdom = 11;
        charisma = 16;
        title = "Orc War Chief";
        isPlayer = false;
        faction = 2;
        hostile = true;
        alive = true;
        icon = "W";
    } 
    public OrcWarChief (int definedHealthPoints, int definedAttackDamage, int definedArmorClass) {
        healthPoints = definedHealthPoints;
        attackDamage = definedAttackDamage;
        armorClass = definedArmorClass;
    }
    
    public void speak() {
        System.out.println(talkText + " dude");
    }

    
    public void add(Room r, Cell c, NPC npc) {
        LongSword s = new LongSword();
        npc.rollNumbers();
        npc.weaponEquiped = s;
        npc.title = npc.title + (Integer.toString(r.roomContains.size() + 1));
        npc.cellAddress = c;
        npc.charPoint.setLocation(c.cellCord);
        npc.roomAddress = r;
        HealingPotion healingPotion = new HealingPotion();
        npc.inventory.add(healingPotion);
        c.cellContains.add(npc);
        r.roomContains.add(npc);
        r.roomNPCs.add(npc);
    }
    
    

}