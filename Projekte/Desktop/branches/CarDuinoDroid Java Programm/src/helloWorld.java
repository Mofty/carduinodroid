import javax.swing.*; 

import java.awt.*; 

import java.awt.event.*; 

public class helloWorld extends JFrame {
    helloWorld(String title) { 
        this.setSize(500,500); 
        setTitle(title); 
    }

    public static void main(String[] args) {
       helloWorld window = new helloWorld("Hello CodeCall");
       window.setVisible(true);
       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}