package combat.game;

/**
 *
 * @author Chris
 */
public class HealthReporter {
    Ticker tick = new Ticker();
        void healthReport(Character c) throws InterruptedException {
        System.out.print(c.title);
        if (c.alive == false) {
            tick.add(" Corpse");
            
        }
        System.out.println(": HP    " + c.healthPoints);

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