package combat.game;

import java.util.Scanner;

public class ExplorationGame {
       public static Scanner in = new Scanner(System.in); // the input Scanner
       Ticker tick = new Ticker();
       
    public void ExplorationGame (Room r, Character player) throws InterruptedException {
         r.drawRoom();
         player.target = player;
         
         explorationGamePrompt(in, player);
         
     }
    public void explorationGamePrompt(Scanner in, Character user) throws InterruptedException {
        user.reportStats();
        System.out.println("Exploration Options: Enter a number and hit enter:\n"
                + "1: Equip Weapon   "
                + "2: Prepare Spell   "
                + "3: Use Item     "
                + "4: Talk  "
                + "5: Move  "
                + "6: Loot body  "
                + "7: Next Room");
        int exploreMode = in.nextInt();  // User input here
        if (exploreMode == 1) {
            selectWeapon(user);
            
        }
        if (exploreMode == 2) {
            prepareSpell(user);
            
        }
        if (exploreMode == 3) {
            System.out.println("Invantory:  Enter item number.");
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
 for (int d = 0; d < user.weaponInventory.size(); d++) {
            System.out.println((d+1) + ":" + user.weaponInventory.get(d).title); 
        } 
        int itemSelect = in.nextInt();  // User input here
        System.out.println(user.weaponInventory.get(itemSelect-1).title);
        user.weaponEquiped = user.weaponInventory.get(itemSelect-1);
    }
    private void prepareSpell(Character user) {
        for (int d = 0; d < user.spellBook.size(); d++) {
            System.out.println((d+1) + ":" + user.spellBook.get(d).title); 
        } 
        int spellSelect = in.nextInt();  // User input here
        System.out.println(user.spellBook.get(spellSelect-1).title + " prepared.");
        user.preparedSpell = user.spellBook.get(spellSelect-1);
    
    
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
    private void openInventoryExplore(Character user) throws InterruptedException {
            System.out.println("0: Close Inventory");
        for (int d = 0; d < user.inventory.size(); d++) {
            
            System.out.println((d+1) + ": " + user.inventory.get(d).title + ""
                    + " (" + user.inventory.get(d).quantity + ")"); 
        } 
        int itemSelect = in.nextInt();  // User input here
        if (itemSelect == 0) {
            explorationGamePrompt (in, user);
        }
        else {
         System.out.println(user.inventory.get(itemSelect-1).title);
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
                tick.add(user.title + " and " + friendly.name + " are speaking.");
                tick.add(friendly.talkText);
                talked = true;
            }
            
        }
        if (talked == false) {
            
            tick.add("There is no one here to speak with.");
        }
    }
    private void exploreMovePrompt(Scanner in, Character user) throws InterruptedException {
        
            
            System.out.println("Enter direction: 2:South 4:West 6:East 8:Noth 5:End Move.");
            user.roomAddress.drawRoom();
            int moveDirection = in.nextInt();  // User input here
            if (moveDirection != 5) {
                user.moveChar(user, moveDirection);
                exploreMovePrompt(in, user);
            }
            
            
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void lootBody(Character user) throws InterruptedException {
        //user.roomAddress.roomContains.get(0).inventory
                Character loot = new Character();
                for (int k = 0; k < user.cellAddress.cellContains.size(); k++) {
                    if (user.cellAddress.cellContains.get(k).alive == false) {
                        loot = user.cellAddress.cellContains.get(k);
                        tick.add("Looting body of " + loot.title);
                         break;
                    }
                }
                   
                
                
            System.out.println("0: Close Inventory");
        for (int d = 0; d < loot.inventory.size(); d++) {
            
            System.out.println((d+1) + ": " + loot.inventory.get(d).title + ""
                    + " (" + loot.inventory.get(d).quantity + ")"); 
        } 
        int itemSelect = in.nextInt();  // User input here
        if (itemSelect == 0) {
            explorationGamePrompt (in, user);
        }
        else {
         tick.add("Looted " + loot.inventory.get(itemSelect-1).title);
         user.inventory.add(loot.inventory.get(itemSelect-1));
         loot.inventory.remove(itemSelect - 1);
        
        if (loot.inventory.size() == 0) {
             user.roomAddress.roomContains.remove(0);
             user.cellAddress.cellContains.remove(0);
                
            }   
        }
        
        
    }
    }
        
    

     
    
