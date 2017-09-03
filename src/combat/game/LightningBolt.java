package combat.game;

/**
 *
 * @author Chris
 */
public class LightningBolt extends Spell{
    Ticker tick = new Ticker();
    Dice dice = new Dice();
    TargetSelector selector = new TargetSelector();
    
    HealthReporter h = new HealthReporter();
    Ruler ruler = new Ruler();
    public LightningBolt() {
        title = "Lightning Bolt";
        //example. use verb for sword is swing, but for sword is shot
        useVerb = "fired";
        range = 6;
        standOff = 0;
    }
    public void use(Character attacker, Character defender, Player player) throws InterruptedException {
        tick.turnOnTicker(player.roomAddress.outPut);
        double dis = ruler.measureDistance(attacker, defender);
        if (dis <= range && dis >= standOff) {
        tick.add(attacker.title + " " + useVerb + " a " + 
                title + " at " + defender.title + "\n");
        int d = dice.rollDice(6) + dice.rollDice(6) + dice.rollDice(6) + dice.rollDice(6)
                + dice.rollDice(6) + dice.rollDice(6) + dice.rollDice(6) + dice.rollDice(6);
        int halfD = d/2;
        int savingThrow = 8 + attacker.proficiencyBonus + attacker.spellAbilityModifier;
            
            int iRoll = dice.rollDice(20);
            if (iRoll <= savingThrow) {
                defender.healthPoints = defender.healthPoints-d;
                tick.add(attacker.title +" hit " + defender.title + ""
                        + " with a " + title + " for " + d + " damage.\n");
                h.checkCharacterLife(defender);
                h.healthReport(defender);
            }
            
            if (iRoll > savingThrow) {
                defender.healthPoints = defender.healthPoints - halfD;
                tick.add(attacker.title +" hit " + defender.title + ""
                        + " with a " + title + " for " + halfD + " damage.\n");
                h.checkCharacterLife(defender);
                h.healthReport(defender);
                
            } 
        } 
      if (h.checkCharacterLife(defender) == false) {
                selector.setNextEnemy (attacker, player);
        }  
        
    
        if (dis > range) {
            tick.add("Target out of " + title + " range");
        }
        if (dis < standOff) {
            tick.add("You are too close to use " + title + ".");
        }
    }

    
    
    }