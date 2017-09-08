package combat.game;

/**
 *
 * @author Chris
 */
public class HealthReporter {
    Ticker tick = new Ticker();
        void healthReport(Character c) throws InterruptedException {
        tick.turnOnTicker(c.roomAddress.outPut);
        tick.addFast(c.title);
        if (c.alive == false) {
            tick.addFast(" Corpse");
            
        }
        tick.addFast(": HP    " + c.healthPoints + "\n");

     }
            public boolean checkCharacterLife(Character c) {
        if (c.healthPoints <= 0) {
            c.healthPoints = 0;
            c.die();
            return false;
        }
        else return true;
    }   
    
}