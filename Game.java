package tankbattle;

import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;

public class Game extends JFrame {
    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        MyPanel mp = null;
        if (Recorder.isEmpty()) {
            mp = new MyPanel(1);
        } else {
            int key;
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println("新游戏——1   继续游戏——2");
                key = scanner.nextInt();
                if (!(key == 1 || key == 2))
                    System.out.println("请输入  1 或者 2");
            } while (!(key == 1 || key == 2));
            mp = new MyPanel(key);
            scanner.close();
        }
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1210, 800);
        this.setTitle("坦克大战");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.setFocusable(true);
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
