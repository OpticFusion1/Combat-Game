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
public class ShortSword extends Weapon{
    public ShortSword() {
        title = "Short Sword";
        //example. use verb for sword is swing, but for sword is shot
        useVerb = "swung";
        goldValue = 500;
        weight = 5;
        range = 1.75;
        standOff = 0;
        attackDamage = 10;
}
    
}
