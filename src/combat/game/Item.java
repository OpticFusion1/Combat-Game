package combat.game;

    public abstract class Item {
        HealthReporter health = new HealthReporter();
        Ticker tick = new Ticker();
        String title;
        int goldValue;
        double weight;
        int healHurt;
        boolean flamible;
        int range;
        Character defaultTarget;
        boolean selfHeal;
        int quantity = 1;
    
    public abstract void use(Character user) throws InterruptedException; /*{
        if (selfHeal == true) {
            defaultTarget = user;
        }
        else {
            defaultTarget = user.target;
        }
        System.out.println(user.title + " used "
                + "" + title + " on " + defaultTarget.title + ". ");
        defaultTarget.healthPoints =  defaultTarget.healthPoints + healHurt;
        health.healthReport(defaultTarget());
        if (flamible == true) {
            defaultTarget.burning = true;
            defaultTarget.burnDuration = 3;
        }
        
        
         if (defaultTarget.healthPoints > defaultTarget.MaxHealth) {
            defaultTarget.healthPoints = defaultTarget.MaxHealth;
            
        }
         if (selfHeal == true) {
             System.out.println("Healed " + healHurt + " health.");
         }
         else {
           System.out.println("Dealt " + (-1*healHurt) + " Damage.");  
         }
         health.healthReport(defaultTarget());
    }
    */}    