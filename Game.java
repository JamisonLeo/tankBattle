package tankbattle;

import javax.swing.*;

import java.awt.event.*;

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
        this.setSize(1210, 800);
        this.setTitle("坦克大战");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        // 监听关闭窗口，将Recorder.hitEnemyTankNum保存到文件当中
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.saveRecord();
                System.exit(0);
            }
        });
    }
}
