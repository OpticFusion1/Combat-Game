package combat.game;

import java.awt.Point;
import java.util.ArrayList;

public class Character {
    
    int stepsLeft;
    Dice dice = new Dice();
    int sight;
    String name;
    Ticker tick = new Ticker();
    int level;
    Point charPoint = new Point();
    double challenge;
    boolean isPlayer;
    boolean hostile;
    Weapon weaponEquiped;
    Spell preparedSpell;
    int spellSlotMax;
    int spellSlotLeft;
    ArrayList<Spell> spellBook = new ArrayList();
    ArrayList<Weapon> weaponInventory = new ArrayList();
    int XP;
    int spotInArray;
    MapFrame cg;
    int sightRange;
    int selectionNumber;
    Cell cellAddress;
    Room roomAddress;
    boolean alive;
    int faction;
    boolean burning;
    int conditionDuration;
    ArrayList<Item> inventory = new ArrayList();
    String icon;
    Ruler ruler = new Ruler();
    String title;
    
    //health stuff
    int MaxHealth;
    int healthPoints;
    int hitPointDie;
    int numberOfHitPointDice;
    int hitPointModifier;
    
    int attackDamage;
    int armorClass;
    int speed;
    int initiative;
    //ability scores
    int strength;
    int strengthModifier;
    int dexterity;
    int dexterityModifier;
    int constitution;
    int constitutionModifier;
    int intelligence;
    int intelligenceModifier;
    int wisdom;
    int wisdomModifier;
    int charisma;
    int charismaModifier;
    int proficiencyBonus;
    Character target;
    int spellAbilityModifier;
    
    public void die() {
        hostile = false;
        alive = false;
        icon = "X";
    }
    public void refreshNumbers() {
        dexterityModifier = (dexterity - 10)/2;
        constitutionModifier = (constitution - 10)/2;
        intelligenceModifier = (intelligence - 10)/2;
        wisdomModifier = (wisdom - 10)/2;
        strengthModifier = (strength - 10)/2;
        charismaModifier = (charisma - 10)/2;
        hitPointModifier = constitutionModifier;
        
        
    }
    public void attackPlayer(Character enemy, Player player) throws InterruptedException {
        double d;
        enemy.target = player;
        d = ruler.measureDistance(enemy, player);
        if (d < enemy.weaponEquiped.range && enemy.alive == true && enemy.hostile == true) {
            player.roomAddress.outPut.weaponAttack(enemy, player, player);
        
    }
}
    void moveChar (Character c, int d) throws InterruptedException {
        tick.turnOnTicker (c.roomAddress.outPut);
        Cell oldPostion = c.cellAddress;
        c.cellAddress.cellAddressCountOff();
        if (d == 2) {
            //go south
            tick.add(c.title + " moved south.\n");
            if (
        c.roomAddress.roomTiles[c.charPoint.y +1][c.charPoint.x].cellStructure.passable == true  ) {
             c.charPoint.translate(0, 1);   
            }  
        
        }
        if (d == 4) {
            //go west
            tick.add(c.title + " moved west.\n");
            if (
        c.roomAddress.roomTiles[c.charPoint.y][c.charPoint.x-1].cellStructure.passable == true) {
             c.charPoint.translate(-1, 0);   
            }    
        
        }
        if (d == 6) {
            //go east
            tick.add(c.title + " moved east.\n");
            if (c.roomAddress.roomTiles[c.charPoint.y][c.charPoint.x+1].cellStructure.passable == true) {
                c.charPoint.translate(1, 0);
            }
        
        }
        if (d == 8) {
            //go north
            tick.add(c.title + " moved north.\n");
        if (c.roomAddress.roomTiles[c.charPoint.y-1][c.charPoint.x].cellStructure.passable == true) {
             c.charPoint.translate(0, -1);
        }    
       
        
        }
        if (c.roomAddress.roomTiles[c.charPoint.y][c.charPoint.x].cellStructure.passable == true) {
           c.roomAddress.roomTiles[c.charPoint.y][c.charPoint.x].cellContains.add(c);
            c.cellAddress = c.roomAddress.roomTiles[c.charPoint.y][c.charPoint.x];
            oldPostion.cellContains.remove(c.spotInArray);
            c.cellAddress.cellAddressCountOff(); 
        }
        else {
            tick.add(c.title + " is blocked by a "
                    + "" + c.roomAddress.roomTiles[c.charPoint.y][c.charPoint.x].cellStructure.title +
                    "\n");
        }
        c.roomAddress.drawRoom();
        //if (c.roomAddress.roomTiles[P.y][P.x]
        
        
    }
    public void rollNumbers() {
        dexterityModifier = (dexterity - 10)/2;
        constitutionModifier = (constitution - 10)/2;
        intelligenceModifier = (intelligence - 10)/2;
        wisdomModifier = (wisdom - 10)/2;
        strengthModifier = (strength - 10)/2;
        charismaModifier = (charisma - 10)/2;
        healthPoints = 0;
        
        for (int d = 0; d < numberOfHitPointDice; d++) {
            healthPoints = healthPoints + dice.rollDice(hitPointDie);
        }
        healthPoints = healthPoints + hitPointModifier;

}
    public void reportStats() throws InterruptedException {
                tick.turnOnTicker (roomAddress.outPut);
        tick.updateInfo("Title:" + title +"  lvl:" + level + "  XP:" + XP + ""
                + "  HP:" +healthPoints + "/" + MaxHealth);
    }
}