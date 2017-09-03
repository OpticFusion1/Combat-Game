/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combat.game;

/**
 *
 * @author Chris
 */
public class Weapon {
        String title;
        //example. use verb for sword is swing, but for sword is shot
        String useVerb;
        int goldValue;
        double weight;
        double range;
        double standOff;
        int attackDamage;
        
 public boolean use(Character user) {
     Ruler r = new Ruler();
     Dice dice = new Dice();
     
 
     double d = r.measureDistance(user, user.target);
        if (d > range) {
        System.out.println("Target out of range.");
        return false;
        }
        if (d <= range) {
        System.out.println(user.title + " " + useVerb + " at " + user.target.title + ""
                + " with a " + title + ".");
        if (dice.rollDice(20) + user.proficiencyBonus >= user.target.armorClass) {
            user.target.healthPoints = user.target.healthPoints-user.attackDamage;
            return true;
        }
        
 }
        
            return false;
         
}
 }


 