/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combat.game;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Chris
 */
public class Dice {
        public int rollDice(int sidedDice) {
        int min = 1;
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int diceResult = ThreadLocalRandom.current().nextInt(min, sidedDice + 1);
        //System.out.println("Hit di rolled " + diceResult);
        return diceResult;
        
    }
}
