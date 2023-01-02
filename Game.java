package tankbattle;

import javax.swing.*;

public class Game extends JFrame {
    public static void main(String[] args) {
        new Game();
    }
    
    public Game() {
        MyPanel mp = new MyPanel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1000, 800);
        this.setTitle("坦克大战");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
