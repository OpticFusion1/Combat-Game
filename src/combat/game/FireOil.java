package combat.game;

public class FireOil extends Item{
    
    HealthReporter health = new HealthReporter();
    Ruler ruler = new Ruler();
    public FireOil () {
        super();
        title = "Firey Throwing Oil";
        goldValue = 50;
        weight = .1;
        healHurt = -5;
        flamible = true;
        selfHeal = false;
        range = 5;
        
    }  

    public void use(Character user) throws InterruptedException {
        double dis = ruler.measureDistance(user, user.target);
        if (dis < range) {
        user.target.healthPoints = user.target.healthPoints + healHurt;
        quantity--;
        user.target.burning = true;
        user.target.conditionDuration = 3;
        if (health.checkCharacterLife(user.target)) {
        health.healthReport(user.target);
        }
        }
        else {
            tick.add("Target out of range");
        }
    }
    }