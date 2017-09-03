package combat.game;

public class Player extends Character{
    
    
    
    public Player () {
        isPlayer = true;
        level = 1;
        XP = 0;
        hitPointDie = 8;
        alive = true;
        faction = 1;
        title = "Player";
        
        hitPointDie = 10;
        numberOfHitPointDice = 1;
        hitPointModifier = 3;
        
        MaxHealth = healthPoints;
        attackDamage = 12;
        armorClass = 18;
        speed = 30;
        initiative = 1;  
        proficiencyBonus = 2;
        icon = "p";
        
    //set up invantory
       /* FireOil fireOil = new FireOil();
        HealingPotion healingPotion = new HealingPotion();
        invantory[0] = fireOil;
        invantory[1] = healingPotion; */
        
    
    //ability scores
        strength = 17;
        dexterity = 12;
        constitution = 16;
        intelligence = 13;
        wisdom = 16;
        charisma = 16;
        spellAbilityModifier = 3;
    }
     void addPlayer (Player p, Room r, Cell c) {
        p.rollNumbers();
        FireOil fireOil = new FireOil();
        LightningBolt lightningBolt = new LightningBolt();
        FireBall fireBall = new FireBall();
        HealingPotion healingPotion = new HealingPotion();
        p.inventory.add(fireOil);
        p.inventory.add(healingPotion);
        p.preparedSpell = lightningBolt;
        p.spellBook.add(lightningBolt);
        p.spellBook.add(fireBall);
        LongSword l = new LongSword();
        ShortSword s = new ShortSword();
        p.weaponEquiped = l;
        p.weaponInventory.add(l);
        p.weaponInventory.add(s);
        p.healthPoints = p.hitPointDie + p.hitPointModifier;
        p.MaxHealth = p.healthPoints;
        

        
        
        
        
        
    }
     void addPlayerToNewRoom (Character p, Room r, Cell c) {
        p.charPoint.x = c.cellCord.x;
        p.charPoint.y = c.cellCord.y; 
        p.cellAddress = c;
        p.roomAddress = r;
        c.cellContains.add(p);
        r.roomContains.add(p);
        }   
     public int getLevel() {
        if (XP >= 300) {
            level = 2;
        }
        if (XP >= 900) {
            level = 3;
        }
        if (XP >= 2700) {
            level = 4;
        }
        if (XP >= 6500) {
            level = 5;
        }
        if (XP >= 14000) {
            level = 6;
        }
        if (XP >= 23000) {
            level = 7;
        }
        if (XP >= 34000) {
            level = 8;
        }
        if (XP >= 48000) {
            level = 9;
        }
        if (XP >= 64000) {
            level = 10;
        }
        if (XP >= 85000) {
            level = 11;
        }
        if (XP >= 100000) {
            level = 12;
        }
        if (XP >= 120000) {
            level = 13;
        }
        if (XP >= 140000) {
            level = 14;
        }
        if (XP >= 165000) {
            level = 15;
        }
        if (XP >= 195000) {
            level = 16;
        }
        if (XP >= 225000) {
            level = 17;
        }
        if (XP >= 265000) {
            level = 18;
        }
        if (XP >= 305000) {
            level = 19;
        }
        if (XP >= 355000) {
            level = 20;
        }
        
      return level;  
    }
     
     public void checkLevelUp() throws InterruptedException {
         int oldLevel = level;
         int newLevel = getLevel();
         if (newLevel > oldLevel) {
             tick.add("Congradulation you are now Level " + level +"\n");
             for (int i = 0; i < (newLevel - oldLevel); i++) {
                levelUp(); 
             }
             
         }
     }
     public void levelUp() throws InterruptedException {
         refreshNumbers();
         int hp = dice.rollDice(hitPointDie) + hitPointModifier;
         MaxHealth = MaxHealth + hp;
         healthPoints = MaxHealth;
         tick.add("You HP has increased by " + hp);
         if (level >= 0) {
            proficiencyBonus = 2;
        }
         if (level >= 5) {
            proficiencyBonus = 3;
        }
          if (level >= 9) {
            proficiencyBonus = 4;
        }
            if (level >= 13) {
            proficiencyBonus = 5;
        }
              if (level >= 17) {
            proficiencyBonus = 6;
        }
     }
     
}
