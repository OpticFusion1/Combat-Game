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
public class Ruler {
        double c;
        double x1;
        double y1;
        double x2;
        double y2;
    
        public double measureDistance (Character A, Character B) {

        x1 = A.cellAddress.cellCord.x;
        y1 = A.cellAddress.cellCord.y;
        x2 = B.cellAddress.cellCord.x;
        y2 = B.cellAddress.cellCord.y;
        c = Math.sqrt(((x2-x1)*(x2-x1)) + ((y2-y1)*(y2-y1)));
        return c;
}
}
