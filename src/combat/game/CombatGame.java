package combat.game;

import java.util.Scanner;
import java.awt.*;
//@author Christian Hodgson
public class CombatGame {
    //cc is short for current cell
    Ruler ruler = new Ruler();
    Ticker tick = new Ticker();
    Dice dice = new Dice();
    int stepsLeft;
    TargetSelector selector = new TargetSelector();
    HealthReporter health = new HealthReporter();
    public static Scanner in = new Scanner(System.in); // the input Scanner
    int turnBeans;
    public boolean crIsHostile;
    public void CombatGame (Room onlyRoom, Player player1) throws InterruptedException {
        turnBeans = 0;
        selector.setNextEnemy(player1, player1);
        displayRoomOccupants (onlyRoom, player1);
        combatManager(player1);
    }
    void combatManager(Player user) throws InterruptedException {
        checkHostility(user.roomAddress, user);
        while (crIsHostile == true) {
            checkHostility(user.roomAddress, user);
            if (crIsHostile == true && user.alive == true) {
            turnBeans++;
            combatRound(in, user);
            }
            if (crIsHostile == true && user.alive == false) {
            showGameOver(user.roomAddress);
            break;
        }
        
    }
    }
    void showGameOver(Room r) throws InterruptedException {
        tick.turnOnTicker(r.outPut);
        r.drawRoom();
        tick.add("\n You have been defeated!");
        
    }
    public void combatRound(Scanner in, Player player) throws InterruptedException{
        tick.turnOnTicker(player.roomAddress.outPut);
        player.roomAddress.drawRoom();
        int moves = player.speed/5;
        tick.add("\nRound " + turnBeans + " Fight! Current target: "
                + player.target.title + "\n");
        tick.add("Distance: " + ruler.measureDistance(player, player.target) + "\n");
        
        
        for (int i = 0; i < player.roomAddress.roomContains.size(); i++) {
            stepsLeft = player.roomAddress.roomContains.get(i).speed/5;
            //This loop dictates the turn order during combat.
            if (player.roomAddress.roomContains.get(i).alive == true 
                    && player.roomAddress.roomContains.get(i).isPlayer == true) {
                
                playerCombatPrompt(in, player);
            }
            else if (checkCharacterHostility(player.roomAddress.roomContains.get(i), player) == true) {
                HostileNPCCombatPrompt(player.roomAddress.roomContains.get(i), player);
            }
                
        }
        
    }
    void HostileNPCCombatPrompt (Character enemy, Player player) throws InterruptedException {
        double d;
        enemy.stepsLeft = enemy.speed/5;
        enemy.target = player;
        d = ruler.measureDistance(enemy, player);
        if (d <= enemy.weaponEquiped.range && enemy.alive == true && enemy.hostile == true) {
            weaponAttack(enemy, player, player);
        }
        if (d > enemy.weaponEquiped.range && enemy.alive == true 
                && enemy.hostile == true  && d < (enemy.sight/5)) {
            rushPlayer(enemy, player);
        }
            
    }
    void rushPlayer (Character enemy, Player player) throws InterruptedException {
        for (int s = 0; s < enemy.speed/5; s++) {
        
        //If the enemy is too far east of the player
        if ((player.cellAddress.cellCord.x + 1) < enemy.cellAddress.cellCord.x
                && enemy.stepsLeft >= 1) {
            enemy.moveChar(enemy, 4);
            enemy.stepsLeft--;
            enemy.roomAddress.drawRoom();
            tick.tock();
        }
        //If the enemy is too far west of the player
        if ((player.cellAddress.cellCord.x - 1) > enemy.cellAddress.cellCord.x
                && enemy.stepsLeft >= 1) {
            enemy.stepsLeft--;
            enemy.moveChar(enemy, 6);
            enemy.roomAddress.drawRoom();
            tick.tock();
        }
        //If the enemy is too far South of the player
        if ((player.cellAddress.cellCord.y + 1) < enemy.cellAddress.cellCord.y
                && enemy.stepsLeft >= 1) {
            enemy.stepsLeft--;
            enemy.moveChar(enemy, 8);
            enemy.roomAddress.drawRoom();
            tick.tock();
        }
        //If the enemy is too far north of the player
        if ((player.cellAddress.cellCord.y - 1) > enemy.cellAddress.cellCord.y
                && enemy.stepsLeft >= 1) {
            enemy.stepsLeft--;
            enemy.moveChar(enemy, 2);
            enemy.roomAddress.drawRoom();
            tick.tock();
        }
            
    }
        weaponAttack(enemy, player, player);
    }
    boolean checkCharacterHostility (Character a, Character b) {
        return a.faction != b.faction;
        
    }
    void playerCombatPrompt (Scanner in, Player user) throws InterruptedException {
        tick.turnOnTicker(user.roomAddress.outPut);
        
       
        user.reportStats();
        tick.updateActionBar("Combat: "
                + "1: " + user.weaponEquiped.title + "   "
                + "2: " + user.preparedSpell.title + "   "
                + "3: Use Item     "
                + "4: Change Target     "
                + "5: Move");
        int attackMode = in.nextInt();  // User input here
        if (attackMode == 1) {
            weaponAttack(user, user.target, user);
            
        }
        if (attackMode == 2) {
            usePreparedSpell(user, user.target, user);
            
        }
        if (attackMode == 3) {
            tick.add("Inventory:  Enter item number.\n");
            openInventory(user, user.target);
            
        }
        if (attackMode == 4) {
            tick.updateActionBar("Enter target number.");
            user.roomAddress.drawRoomSelection ();
            int newTargetNumber = in.nextInt();  // User input here
            user.target = user.roomAddress.roomContains.get(newTargetNumber-1);
            tick.add("New target: "
                + user.target.title + "\n");
            tick.add("Distance: " + ruler.measureDistance(user, user.target) + "\n");
            user.roomAddress.drawRoom();
            playerCombatPromptNoMove(in, user);
            
        }
        if (attackMode == 5) {
            for (int moves = stepsLeft; moves > 0; moves--) {
            tick.add("You have " + moves + " left.\n");
            tick.updateActionBar("Enter direction: 2:South 4:West 6:East 8:Noth 5:End Move.");
            user.roomAddress.drawRoom();
            int moveDirection = in.nextInt();  // User input here
            if (moveDirection == 5) {
            break;
            }
            user.moveChar(user, moveDirection);
            }
            tick.add("Distance: " + ruler.measureDistance(user, user.target) + "\n");
            playerCombatPromptNoMove(in, user);
            
        }
    }
    void playerCombatPromptNoMove (Scanner in, Player user) throws InterruptedException {
        
        

        
        
        tick.updateActionBar("Combat:"
                + "1: " + user.weaponEquiped.title + "   "
                + "2: " + user.preparedSpell.title + "   "
                + "3: Use Item     "
                + "4: Change Target");
        int attackMode = in.nextInt();  // User input here
        if (attackMode == 1) {
            weaponAttack(user, user.target, user);
            
        }
        if (attackMode == 2) {
            usePreparedSpell(user, user.target, user);
            
        }
        if (attackMode == 3) {
            tick.updateActionBar("Invantory:  Enter item number.");
            openInventory(user, user.target);
            
        }
        if (attackMode == 4) {
            tick.updateActionBar("Enter target number.");
            user.roomAddress.drawRoomSelection ();
            int newTargetNumber = in.nextInt();  // User input here
            user.target = user.roomAddress.roomContains.get(newTargetNumber-1);
            tick.add("New target: "
                + user.target.title + "\n");
            tick.add("Distance: " + ruler.measureDistance(user, user.target) + "\n");
            playerCombatPromptNoMove(in, user);
            
        }
    }
    void displayRoomOccupants (Room r, Player player) throws InterruptedException {
         tick.turnOnTicker(r.outPut);
        tick.add("Characters in Combat: \n");
        for (int i = 0; i < r.roomContains.size(); i++) {
            if (r.roomContains.get(i).alive == true) {
            tick.addFast(r.roomContains.get(i).title +". ");
        }
        }
        tick.addFast("\n");
        describeCharacter(player);
        
        for (int i = 0; i < r.roomContains.size(); i++) {
            if (r.roomContains.get(i).alive == true 
                    && r.roomContains.get(i).isPlayer == false) {
            health.healthReport(r.roomContains.get(i));
        }
        }
    }

    void describeCharacter(Character c) throws InterruptedException {
        tick.turnOnTicker(c.roomAddress.outPut);
        tick.add("\n" + c.title);
        if (c.alive == false) {
            tick.addFast(" Corpse\n");
        }
        tick.addFast("\nHP: " + c.healthPoints + "\nArmor:"
                + "" + c.armorClass + "\nAttack" + c.attackDamage + "\n");

     }    
    public void weaponAttack(Character attacker, Character defender, Player player) throws InterruptedException {
        if (attacker.weaponEquiped.use(attacker)) {
            if (health.checkCharacterLife(defender) == false) {
                selector.setNextEnemy (attacker, player);
            }
            tick.add("It's a Hit! " + defender.title + " lost " 
                    + attacker.attackDamage + " health.\n");
            health.healthReport(defender);
        }
        else {
            tick.add("Miss!\n");
            
        }
    }
    public void usePreparedSpell(Character attacker, Character defender, Player player)  throws InterruptedException {
        attacker.preparedSpell.use(attacker, defender, player);
    }    
    public void openInventory(Player user, Character target) throws InterruptedException {
            String s = new String("");
            s += ("0: Close Inventory");
            
        for (int d = 0; d < user.inventory.size(); d++) {
            
            s +=((d+1) + ": " + user.inventory.get(d).title + ""
                    + " (" + user.inventory.get(d).quantity + ")"); 
        } 
        tick.updateActionBar(s);
        int itemSelect = in.nextInt();  // User input here
        if (itemSelect == 0) {
            playerCombatPromptNoMove (in, user);
        }
        else {
         tick.add(user.inventory.get(itemSelect-1).title + "\n");
        user.inventory.get(itemSelect-1).use(user);
        for (int d = 0; d < user.inventory.size(); d++) {
            if (user.inventory.get(d).quantity == 0) {
                user.inventory.remove(d);
            }   
        }
        
    }
    
    }   
    void checkHostility(Room r, Player player) throws InterruptedException {
        for (int i = 0; i < r.roomContains.size(); i++) {
            if (r.roomContains.get(i).hostile == true) {
                crIsHostile = true;
                break;
            }
            crIsHostile = false;
        }
            
            if (crIsHostile == false && player.alive == true) {
                for (int i = 0; i < r.roomNPCs.size(); i++) {
                    r.encounterXP = r.encounterXP + r.roomNPCs.get(i).XP;
                }
                player.XP = player.XP + r.encounterXP;
                tick.add("You gained " + r.encounterXP + " XP.\n");
                player.checkLevelUp();
                
                
                r.drawRoom();
            tick.add("\nAll enemies in this room lay dead.\n");
            }
        }
}