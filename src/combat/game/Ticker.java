package combat.game;

import java.util.ArrayList;

public class Ticker {
    ArrayList<String> scroll = new ArrayList();
    int printedLine;
    
  void add(String s) throws InterruptedException {
    scroll.add(s);
    Thread.sleep(300);
    System.out.println(s);
}  
  void tock() throws InterruptedException {
      Thread.sleep(500);
  }
}
