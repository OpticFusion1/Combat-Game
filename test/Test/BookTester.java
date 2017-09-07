package Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BookTester {
    Book book = new Book();
    KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            book.page.setText("Player typed a key");
            if (e.getKeyChar() == '8') {book.page.setText("Player typed 8");}
             if (e.getKeyChar() == '2') {book.page.setText("Player typed 8");}
        }

        @Override
        public void keyPressed(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyReleased(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    public BookTester () {
        book.page.addKeyListener(keyListener);
    }
    public static void main(String[] arguments) {
        BookTester bookTester = new BookTester();
        bookTester.book.runBook();
        
        
        boolean playerWantsToQuit = false;
        while (playerWantsToQuit = false) {
            
            
        }
         {
                
            }
        
    }
}