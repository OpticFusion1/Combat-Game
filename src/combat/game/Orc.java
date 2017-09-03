package combat.game;

public class Orc extends NPC {
    boolean bloodLust;
    
    public Orc () {
        
        super();
        sight = 60;
        hitPointDie = 8;
        numberOfHitPointDice = 2;
        hitPointModifier = 6;
        
        challenge = .5;
        XP = 200;
        MaxHealth = healthPoints;
        attackDamage = 7;
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
        title = "Orc";
        isPlayer = false;
        faction = 2;
        hostile = true;
        alive = true;
        icon = "O";
        
    } 
    public Orc (int definedHealthPoints, int definedAttackDamage, int definedArmorClass) {
        healthPoints = definedHealthPoints;
        attackDamage = definedAttackDamage;
        armorClass = definedArmorClass;
    }
    
    public void speak() {
        System.out.println(talkText + " dude");
    }

    public void add(Room r, Cell c, NPC npc) {
        ShortSword s = new ShortSword();
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