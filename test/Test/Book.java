package Test;

import java.awt.*;
import javax.swing.*;

class Book extends JFrame {
    JTextArea page = new JTextArea("Hello");
    

public static void runBook() {
    Book book = new Book();
    book.setupBook(book);
}
public Book () {
        super("Book");
        
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
}
void setupBook(Book b) {
    b.add(page);
    b.page.setEditable(false);
    
    b.setVisible(true);
}

}

