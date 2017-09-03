package combat.game;

public class HealingPotion extends Item{
        HealthReporter h = new HealthReporter();
      public HealingPotion () {
          super();
        title = "Potion of Greater Healing";
        goldValue = 100;
        weight = .1;
        healHurt = 16;
        selfHeal = true;
        
        
        
      }

    public void use(Character user) throws InterruptedException {
        user.healthPoints = user.healthPoints + healHurt;
        if (user.MaxHealth < user.healthPoints) {
            user.healthPoints = user.MaxHealth;
        }
        quantity--;
        h.healthReport(user.target);
    }
}
