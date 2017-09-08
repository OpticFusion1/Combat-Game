package combat.game;

/**
 *
 * @author Chris
 */
public class FireBall extends Spell{
     Ticker tick = new Ticker();
    Dice dice = new Dice();
    TargetSelector selector = new TargetSelector();
    HealthReporter h = new HealthReporter();
    Ruler ruler = new Ruler();
    public FireBall() {
        title = "Fire Ball";
        //example. use verb for sword is swing, but for sword is shot
        useVerb = "threw";
        range = 5;
        standOff = 0;
    }
    public void use(Character attacker, Character defender, Player player) throws InterruptedException {
         tick.turnOnTicker(player.roomAddress.outPut);
        double dis = ruler.measureDistance(attacker, defender);
        if (dis <= range && dis >= standOff) {
        Cell blastCell;
        blastCell = defender.cellAddress;
        tick.add(attacker.title + " throws a fire ball at " + defender.title + "\n");
        int d = dice.rollDice(6) + dice.rollDice(6) + dice.rollDice(6) + dice.rollDice(6)
                + dice.rollDice(6) + dice.rollDice(6) + dice.rollDice(6) + dice.rollDice(6);
        int halfD = d/2;
        int savingThrow = 8 + attacker.proficiencyBonus + attacker.spellAbilityModifier;
        for (int i = 0; i < blastCell.cellContains.size(); i++){
            if (blastCell.cellContains.get(i).alive == false) {
                break;
            }
            int iRoll = dice.rollDice(20);
            if (iRoll <= savingThrow) {
                blastCell.cellContains.get(i).healthPoints = blastCell.cellContains.get(i).healthPoints-d;
                tick.add(attacker.title +" hit " + blastCell.cellContains.get(i).title + ""
                        + " with a fire ball causing " + d + " damage.\n");
                h.checkCharacterLife(blastCell.cellContains.get(i));
                h.healthReport(blastCell.cellContains.get(i));
            }
            
            if (iRoll > savingThrow) {
                blastCell.cellContains.get(i).healthPoints = blastCell.cellContains.get(i).healthPoints - halfD;
                tick.add(attacker.title +" hit " + blastCell.cellContains.get(i).title + ""
                        + " with a fire ball causing " + halfD + " damage.\n");
                h.checkCharacterLife(blastCell.cellContains.get(i));
                h.healthReport(blastCell.cellContains.get(i));
                
            } 
        } 
      if (h.checkCharacterLife(defender) == false) {
                selector.setNextEnemy (attacker, player);
        }  
        
    }
        if (dis > range) {
            tick.add("Target out of Fire Ball range\n");
        }
        if (dis < standOff) {
            tick.add("You are too close to use Fire Ball\n");
        }
    }

    
    
    }