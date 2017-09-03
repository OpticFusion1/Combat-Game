package combat.game;

/**
 *
 * @author Chris
 */
public abstract class Spell {
        Ticker tick = new Ticker();
        String title;
        //example. use verb for sword is swing, but for sword is shot
        String useVerb;
        double range;
        double standOff;
        int attackDamage;
        
 public abstract void use(Character attacker, Character defender, Player player) throws InterruptedException;
}