package combat.game;

public class Goblin extends NPC {
    boolean bloodLust;
    
    public Goblin () {
        
        super();
        sight = 20;
        hitPointDie = 6;
        numberOfHitPointDice = 2;
        hitPointModifier = 0;
        
        challenge = .25;
        XP = 100;
        MaxHealth = healthPoints;
        attackDamage = 5;
        armorClass = 15;
        speed = 30;
        initiative = 1;
        proficiencyBonus = 0;
    
    //ability scores
        strength = 8;
        dexterity = 14;
        constitution = 10;
        intelligence = 10;
        wisdom = 8;
        charisma = 8;
        title = "Goblin";
        isPlayer = false;
        faction = 2;
        hostile = true;
        alive = true;
        icon = "G";
        
    } 
    public Goblin (int definedHealthPoints, int definedAttackDamage, int definedArmorClass) {
        healthPoints = definedHealthPoints;
        attackDamage = definedAttackDamage;
        armorClass = definedArmorClass;
    }
    
    public void speak() {
        System.out.println(talkText + " dude");
    }


    public void add(Room r, Cell c, NPC g) {
        ShortSword s = new ShortSword();
        g.rollNumbers();
        g.weaponEquiped = s;
        g.title = g.title + (Integer.toString(r.roomContains.size() + 1));
        g.cellAddress = c;
        g.charPoint.setLocation(c.cellCord);
        g.roomAddress = r;
        HealingPotion healingPotion = new HealingPotion();
        g.inventory.add(healingPotion);
        c.cellContains.add(g);
        r.roomContains.add(g);
        r.roomNPCs.add(g);
    }
    
    

}