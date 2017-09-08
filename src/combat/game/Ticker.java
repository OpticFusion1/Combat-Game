package combat.game;



public class Ticker {
    MapFrame outPut;
    void turnOnTicker (MapFrame f) {
        outPut = f;
    }
    

    int printedLine;
    
  void add(String s) throws InterruptedException {
    Thread.sleep(300);
    outPut.tickerTape(s);
}  
    void addFast(String s) {
    outPut.tickerTape(s);
    }
  void tock() throws InterruptedException {
      Thread.sleep(500);
  }
  void updateInfo(String s) {
      outPut.displayInfo(s);
          }
  void updateActionBar(String s) {
      outPut.displayActions(s);
          }
  
  
}
