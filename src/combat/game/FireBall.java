package combat.game;

/**
 *
 * @author Chris
 */
public class FireBall extends Spell{
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
        double dis = ruler.measureDistance(attacker, defender);
        if (dis <= range && dis >= standOff) {
        Cell blastCell;
        blastCell = defender.cellAddress;
        tick.add(attacker.title + " throws a fire ball at " + defender.title);
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
                        + " with a fire ball causing " + d + " damage.");
                h.checkCharacterLife(blastCell.cellContains.get(i));
                h.healthReport(blastCell.cellContains.get(i));
            }
            
            if (iRoll > savingThrow) {
                blastCell.cellContains.get(i).healthPoints = blastCell.cellContains.get(i).healthPoints - halfD;
                tick.add(attacker.title +" hit " + blastCell.cellContains.get(i).title + ""
                        + " with a fire ball causing " + halfD + " damage.");
                h.checkCharacterLife(blastCell.cellContains.get(i));
                h.healthReport(blastCell.cellContains.get(i));
                
            } 
        } 
      if (h.checkCharacterLife(defender) == false) {
                selector.setNextEnemy (attacker, player);
        }  
        
    }
        if (dis > range) {
            System.out.println("Target out of Fire Ball range");
        }
        if (dis < standOff) {
            System.out.println("You are too close to use Fire Ball");
        }
    }

    
    
    }