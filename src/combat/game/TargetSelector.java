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
public class TargetSelector {
    Ruler ruler = new Ruler();
    double oldD;
    double newD;
        void setNextEnemy (Character c, Player player) {
        if (c.isPlayer != true) {
            c.target = player;
        }
        else {
        for (int i = 0; i < c.roomAddress.roomContains.size(); i++) {
            if ((c.roomAddress.roomContains.get(i).hostile) == true) {
                c.target = c.roomAddress.roomContains.get(i);
            }
        }
        for (int i = 0; i < c.roomAddress.roomContains.size(); i++) {
            oldD = ruler.measureDistance(c, c.target);
            newD = ruler.measureDistance(c, c.roomAddress.roomContains.get(i));
            if ((c.roomAddress.roomContains.get(i).hostile) == true) {
                if (newD < oldD) {
                c.target = c.roomAddress.roomContains.get(i);
            }
        }
    }
    }
    }
}
