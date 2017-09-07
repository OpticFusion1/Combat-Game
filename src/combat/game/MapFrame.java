package combat.game;

import java.util.Scanner;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class MapFrame extends JFrame {
    
    int promptMode;
    KeyListener key;
    int chose;
    boolean awaitingInput = true;
    Ruler ruler = new Ruler();
    Ticker tick = new Ticker();
    Dice dice = new Dice();
    int stepsLeft;
    TargetSelector selector = new TargetSelector();
    HealthReporter health = new HealthReporter();
    public static Scanner in = new Scanner(System.in); // the input Scanner
    int turnBeans;
    public boolean crIsHostile;
    ArrayList<String> scroll = new ArrayList();
    JPanel row1 = new JPanel();
    JLabel infoBar = new JLabel("Info Bar");
    JLabel actionBar = new JLabel("Action Bar");
    JPanel row2 = new JPanel();
    JTextArea mapArea = new JTextArea();
    JTextArea actionArea = new JTextArea();    
    DefaultCaret caret = (DefaultCaret)actionArea.getCaret();
    JScrollPane scrollPanel = new JScrollPane(actionArea);
    
    Map map = new Map();
    Player player1 = new Player();
    
    public MapFrame () {
        super("Adventure Map");

        
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        scrollPanel.add(actionArea);
        actionArea.setEditable(false);
        mapArea.setEditable(false);
        mapArea.setFocusable(false);
        actionArea.addKeyListener(key);
        actionArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                actionAreaKeyTyped(evt);
            }
        });
        scrollPanel.setViewportView(actionArea);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BoxLayout row1Layout = new BoxLayout(row1, BoxLayout.Y_AXIS);
        row1.setLayout(row1Layout);
        row1.add(infoBar);
        row1.add(actionBar);
        
        GridLayout row2Layout = new GridLayout(1,2);
        row2.setLayout(row2Layout);

        row2.add(scrollPanel);
        row2.add(mapArea);
        
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        //setLookAndFeel();
        add(row1, BorderLayout.NORTH);
        add(row2, BorderLayout.CENTER);
        setVisible(true);
    }
    public static void main(String[] args) throws InterruptedException {
        MapFrame a = new MapFrame();
        
        a.startAdventure(a.map, a.player1);
}
    void addPlayerToRoom(Player p, Room r) {
        player1.addPlayer(p, r, r.roomTiles[1][1]);
    }
    void startAdventure (Map map, Player player1) throws InterruptedException {
        //  Room one starts a little bit differantly.  
        // It is outside the for loop that does the rest of the rooms.
        map.createRedKeep(this);
        addPlayerToRoom (player1, map.redKeep.get(0));
        if (player1.alive == true) {
            player1.addPlayerToNewRoom (player1, map.redKeep.get(0), map.redKeep.get(0).roomTiles[1][1]);
            CombatGame(map.redKeep.get(0), player1);
        }
        if (player1.alive == true) {
            ExplorationGame(map.redKeep.get(0), player1); 
        }
        //This for loops runs the rest of the rooms, starting with the 2nd room,
        // That is why int i starts at 1 instead of 0.  
        //The final room must also be seperate, so that the game ends.
        for ( int i = 1; i < map.redKeep.size()-1; i++) {
          if (player1.alive == true) {
            player1.addPlayerToNewRoom (player1, map.redKeep.get(i), map.redKeep.get(i).roomTiles[1][1]);
            CombatGame(map.redKeep.get(i), player1);
        }
        if (player1.alive == true) {
           ExplorationGame(map.redKeep.get(i), player1); 
        }  
        }
        if (player1.alive == true) {
            Room finalRoom = map.redKeep.get(map.redKeep.size()-1);
            player1.addPlayerToNewRoom (player1, finalRoom, finalRoom.roomTiles[1][1]);
            CombatGame(finalRoom, player1);
        }
        if (player1.alive == true) {
            player1.tick.addFast("Victory! You have cleared the Red Keep!");
        }
        }
    void actionAreaKeyTyped(java.awt.event.KeyEvent evt) {
        
        //actionArea.append("\n You clicked something.");
        char k = evt.getKeyChar();
        //actionArea.append("\nYou pressed " + k);
        if (k == '1') {
            chose = 1;
    }
        if (k == '2') {
            chose = 2;
    }
        if (k == '3') {
            chose = 3;
    }
        if (k == '4') {
            chose = 4;
    }
        if (k == '5') {
            chose = 5;
    }
        if (k == '6') {
            chose = 6;
    }
        if (k == '7') {
            chose = 7;
    }
        if (k == '8') {
            chose = 8;
    }
        if (k == '9') {
            chose = 9;
    }
        awaitingInput = false;
        
}
    void displayRoom (Room r) {
        mapArea.setText("");
        for (int i = 0; i < r.roomDisplay.size(); i++) {
            mapArea.append(r.roomDisplay.get(i));
        }
    }
    void displayInfo(String info) {
        infoBar.setText(info);
    }
    void displayActions(String actions) {
        actionBar.setText(actions);
    }
    void tickerTape (String s) {

        scroll.add(s);
        actionArea.setText("");
        for (int i = 0; i < scroll.size(); i++) {
            actionArea.append(scroll.get(i));
        }
    }
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
            "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
            );
        } catch (Exception exc) {
            // ignore error
        }
    }

    //cc is short for current cell

    public void CombatGame (Room onlyRoom, Player player1) throws InterruptedException {
        System.out.println("Start CombatGame");
        player1.spellSlotLeft = player1.spellSlotMax;
        turnBeans = 0;
        selector.setNextEnemy(player1, player1);
        displayRoomOccupants (onlyRoom, player1);
        combatManager(player1);
    }
    void combatManager(Player user) throws InterruptedException {
        System.out.println("Start CombatManager");
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
    void waitForInput() {
        chose = 0;
        awaitingInput = true;
        while (awaitingInput) {
            System.out.println("Waiting");
            if (awaitingInput==false) {
                break;
            }
        }
    }
    void playerCombatPrompt (Scanner in, Player user) throws InterruptedException {
        
        tick.turnOnTicker(user.roomAddress.outPut);
        user.reportStats();
        tick.updateActionBar("Combat: "
                + "1: " + user.weaponEquiped.title + "   "
                + "2: " + user.preparedSpell.title + " (" +user.spellSlotLeft+")  "
                + "3: Use Item     "
                + "4: Change Target     "
                + "5: Move");
        //int attackMode = in.nextInt();  // User input here
        
        waitForInput();
        
        if (chose == 1) {
            weaponAttack(user, user.target, user);
            
        }
        if (chose == 2) {
            usePreparedSpell(user, user.target, user);
            
        }
        if (chose == 3) {
            tick.add("Inventory:  Enter item number.\n");
            openInventory(user, user.target);
            
        }
        if (chose == 4) {
            changeTarget(in, user);

            
        }
        if (chose == 5) {
            moveMode(user);
            
            
        }
    }
    void moveMode(Player user) throws InterruptedException{
        promptMode = 11;
        
       for (int moves = stepsLeft; moves > 0; moves--) {
            tick.add("You have " + moves + " left.\n");
            tick.updateActionBar("Enter direction: 2:South 4:West 6:East 8:Noth 5:End Move.");
            user.roomAddress.drawRoom();
            waitForInput();
            if (chose == 5) {
            break;
            }
            user.moveChar(user, chose);
            }
            tick.add("Distance: " + ruler.measureDistance(user, user.target) + "\n");
            playerCombatPromptNoMove(in, user); 
    }
    void changeTarget(Scanner in, Player user) throws InterruptedException {
            promptMode = 10;
            
            tick.updateActionBar("Enter target number.");
            user.roomAddress.drawRoomSelection ();
            
            waitForInput();
            user.target = user.roomAddress.roomContains.get(chose-1);
            tick.add("New target: "
                + user.target.title + "\n");
            tick.add("Distance: " + ruler.measureDistance(user, user.target) + "\n");
            user.roomAddress.drawRoom();
            playerCombatPromptNoMove(in, user);
    }
    void playerCombatPromptNoMove (Scanner in, Player user) throws InterruptedException {
        promptMode = 2;
        
        tick.updateActionBar("Combat:"
                + "1: " + user.weaponEquiped.title + "   "
                + "2: " + user.preparedSpell.title + " (" +user.spellSlotLeft+")  "
                + "3: Use Item     "
                + "4: Change Target");
        waitForInput();
        int attackMode = chose;  // User input here
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
           changeTarget(in, user);}
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
        if (attacker.spellSlotLeft >= 1) {
            attacker.spellSlotLeft--;
            attacker.preparedSpell.use(attacker, defender, player); 
        }
        else {
            tick.add("\nYou have no prepared spells.");
        }
        
    }    
    public void openInventory(Player user, Character target) throws InterruptedException {
        promptMode = 3;
            String s = new String("");
            s += ("0: Close Inventory");
            
        for (int d = 0; d < user.inventory.size(); d++) {
            
            s +=((d+1) + ": " + user.inventory.get(d).title + ""
                    + " (" + user.inventory.get(d).quantity + ")"); 
        } 
        tick.updateActionBar(s);
        waitForInput();
        int itemSelect = chose;
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
    



       
    public void ExplorationGame (Room r, Character player) throws InterruptedException {
        
         r.drawRoom();
         player.target = player;
         
         explorationGamePrompt(in, player);
         
     }
    public void explorationGamePrompt(Scanner in, Character user) throws InterruptedException {
        promptMode = 4;
         tick.turnOnTicker(user.roomAddress.outPut);
        user.reportStats();
        tick.updateActionBar("Exploration:"
                + "1: Equip Weapon   "
                + "2: Prepare Spell   "
                + "3: Use Item     "
                + "4: Talk  "
                + "5: Move  "
                + "6: Loot body  "
                + "7: Next Room");
        waitForInput();
        int exploreMode = chose;  // User input here
        if (exploreMode == 1) {
            selectWeapon(user);
            
        }
        if (exploreMode == 2) {
            prepareSpell(user);
            
        }
        if (exploreMode == 3) {
            tick.updateActionBar("Invantory:  Enter item number.");
            openInventoryExplore(user);
            
        }
        if (exploreMode == 4) {
            talkToNPC(user);
            
        }
        if (exploreMode == 5) {
            exploreMovePrompt(in, user);
            
            }
         if (exploreMode == 6) {
            lootBody(user);
         }
         if (exploreMode != 7) {
             explorationGamePrompt(in, user);
             //just let it end the method
         }
    }
    private void selectWeapon(Character user) {
        promptMode = 5;
        String s = new String("");
 for (int d = 0; d < user.weaponInventory.size(); d++) {
            s += ((d+1) + ":" + user.weaponInventory.get(d).title + "  "); 
        } 
        tick.updateActionBar(s);
        waitForInput();
        int itemSelect = chose;  // User input here
        tick.addFast(user.weaponInventory.get(itemSelect-1).title);
        user.weaponEquiped = user.weaponInventory.get(itemSelect-1);
    }
    private void prepareSpell(Character user) {
        promptMode = 6;
        String s = new String("");
        for (int d = 0; d < user.spellBook.size(); d++) {
            s += ((d+1) + ":" + user.spellBook.get(d).title); 
        } 
        tick.updateActionBar(s);
        waitForInput();
        int spellSelect = chose;  // User input here
        tick.addFast(user.spellBook.get(spellSelect-1).title + " prepared.");
        user.preparedSpell = user.spellBook.get(spellSelect-1);
    
    
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
    private void openInventoryExplore(Character user) throws InterruptedException {
        promptMode = 7;
        String s = new String("");
            s += ("0: Close Inventory  ");
        for (int d = 0; d < user.inventory.size(); d++) {
            
            s += ((d+1) + ": " + user.inventory.get(d).title + ""
                    + " (" + user.inventory.get(d).quantity + ")  "); 
        } 
        tick.updateActionBar(s);
        waitForInput();
        int itemSelect = chose;  // User input here
        if (itemSelect == 0) {
            explorationGamePrompt (in, user);
        }
        else {
         tick.addFast("Used " + user.inventory.get(itemSelect-1).title);
        user.inventory.get(itemSelect-1).use(user);
        for (int d = 0; d < user.inventory.size(); d++) {
            if (user.inventory.get(d).quantity <= 0) {
                user.inventory.remove(d);
            }   
        }
        
    }
    
    }
    private void talkToNPC(Character user) throws InterruptedException {
        boolean talked = false;
        NPC friendly;
        for (int i = 0; i < user.roomAddress.roomNPCs.size(); i++) {
            if (user.roomAddress.roomNPCs.get(i).faction == 1) {
                friendly = user.roomAddress.roomNPCs.get(i);
                tick.add("\n"+user.title + " and " + friendly.name + " are speaking.");
                tick.add("\n"+friendly.talkText);
                talked = true;
            }
            
        }
        if (talked == false) {
            
            tick.add("There is no one here to speak with.");
        }
    }
    private void exploreMovePrompt(Scanner in, Character user) throws InterruptedException {
        promptMode = 8;
        
            
            tick.updateActionBar("Enter direction: 2:South 4:West 6:East 8:Noth 5:End Move.");
            user.roomAddress.drawRoom();
            waitForInput();
            int moveDirection = chose;  // User input here
            if (moveDirection != 5) {
                user.moveChar(user, moveDirection);
                exploreMovePrompt(in, user);
            }
            
            
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void lootBody(Character user) throws InterruptedException {
        promptMode = 9;
        String s = new String("");
        //user.roomAddress.roomContains.get(0).inventory
                Character loot = new Character();
                for (int k = 0; k < user.cellAddress.cellContains.size(); k++) {
                    if (user.cellAddress.cellContains.get(k).alive == false) {
                        loot = user.cellAddress.cellContains.get(k);
                        tick.add("\nLooting body of " + loot.title);
                         break;
                    }
                }
                   
                
                
            s += ("0: Close Inventory");
        for (int d = 0; d < loot.inventory.size(); d++) {
            
            s += ((d+1) + ": " + loot.inventory.get(d).title + ""
                    + " (" + loot.inventory.get(d).quantity + ")"); 
        } 
        tick.updateActionBar(s);
        waitForInput();
        int itemSelect = chose;  // User input here
        if (itemSelect == 0) {
            explorationGamePrompt (in, user);
        }
        else {
         tick.add("\nLooted " + loot.inventory.get(itemSelect-1).title);
         user.inventory.add(loot.inventory.get(itemSelect-1));
         loot.inventory.remove(itemSelect - 1);
        
        if (loot.inventory.size() == 0) {
             user.roomAddress.roomContains.remove(0);
             user.cellAddress.cellContains.remove(0);
                
            }   
        }
        
        
    }


    }
        
    

     
    


