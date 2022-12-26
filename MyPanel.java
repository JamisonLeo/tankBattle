package tankbattle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    static Tank myTank = null; // 我方坦克
    static Vector<Tank> enemyTanks = new Vector<>(); // 敌方坦克
    int enemyTankSize = 3; // 敌方坦克数量
    // 敌方坦克子弹集合
    static Vector<Shot> shots = new Vector<>();
    // 定义一个Vector，用来存放爆炸
    static Vector<Bomb> bombs = new Vector<>();
    // 定义三张图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel() {
        // 创建我方坦克
        myTank = new MyTank(100, 100);
        myTank.setDirection(Direction.DOWN);

        // 创建敌方坦克
        for (int i = 0; i < enemyTankSize; i++) {
            // 创建一个敌方坦克，并启动线程
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 700);
            new Thread(enemyTank).start();

            // 加入
            enemyTanks.add(enemyTank);
        }

        // 初始化爆炸效果的图片
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/tankBattle/bomb_1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/tankBattle/bomb_2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/tankBattle/bomb_3.png"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(5, 0, 1200, 945);
        g.setColor(Color.black);

        // 画出我方坦克
        drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirection(), 0);

        // 画出我方坦克子弹
        drawShots(g, ((MyTank) myTank).getShots());

        // 画出爆炸效果
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.getLife() > 27)
                g.drawImage(image1, bomb.getX(), bomb.getY(), 90, 90, this);
            else if (bomb.getLife() > 18)
                g.drawImage(image2, bomb.getX(), bomb.getY(), 90, 90, this);
            else
                g.drawImage(image3, bomb.getX(), bomb.getY(), 90, 90, this);
            bomb.subLife();
            if (bomb.getLife() == 0)
                bombs.remove(bomb);
        }

        // 画出敌方坦克
        for (Tank enemyTank : enemyTanks) {
            if (enemyTank.isLive()) {
                // 画出坦克
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 1);
            }
        }
        // 画出敌方坦克的所有子弹
        drawShots(g, getShots());
    }

    /**
     * 画出坦克所有的子弹
     * @param g     画笔
     * @param shots 该坦克的子弹集合
     */
    public void drawShots(Graphics g, Vector<Shot> shots) {
        for (Shot shot : shots) {
            if (shot.isLive()) {
                g.setColor(Color.white);
                g.fillOval(shot.getX(), shot.getY(), 8, 8);
            }
        }
    }

    /**
     * 画出坦克
     * @param x      坦克左上角x轴坐标
     * @param y      坦克左上角y轴坐标
     * @param g      画笔
     * @param direct 坦克方向
     * @param type   坦克类型(0-己方坦克，1-敌方坦克)
     */
    public void drawTank(int x, int y, Graphics g, Direction direct, int type) {
        switch (type) {
            case 0: // 我方坦克颜色
                g.setColor(new Color(0, 255, 255));
                break;
            case 1: // 敌方坦克颜色
                g.setColor(new Color(246, 85, 85));
                break;
        }

        switch (direct) {
            // 向上的坦克
            case UP:
                g.fill3DRect(x, y, 15, 90, true); // 左轮子
                g.fill3DRect(x + 45, y, 15, 90, true); // 右轮子
                g.fill3DRect(x + 15, y + 15, 30, 60, true); // 中间部分
                g.setColor(Color.gray);
                g.fillOval(x + 15, y + 30, 30, 30); // 顶
                g.fill3DRect(x + 28, y - 20, 4, 50, true); // 炮筒
                g.fill3DRect(x + 26, y - 20, 8, 20, true); // 炮筒头
                break;
            // 向下的坦克
            case DOWN:
                g.fill3DRect(x, y, 15, 90, true); // 左轮子
                g.fill3DRect(x + 45, y, 15, 90, true); // 右轮子
                g.fill3DRect(x + 15, y + 15, 30, 60, true); // 中间部分
                g.setColor(Color.gray);
                g.fillOval(x + 15, y + 30, 30, 30); // 顶
                g.fill3DRect(x + 28, y + 60, 4, 50, true); // 炮筒
                g.fill3DRect(x + 26, y + 90, 8, 20, true); // 炮筒头
                break;
            // 向左的坦克
            case LEFT:
                g.fill3DRect(x - 15, y + 15, 90, 15, true); // 左轮子
                g.fill3DRect(x - 15, y + 60, 90, 15, true); // 右轮子
                g.fill3DRect(x, y + 30, 60, 30, true); // 中间部分
                g.setColor(Color.gray);
                g.fillOval(x + 15, y + 30, 30, 30); // 顶
                g.fill3DRect(x - 35, y + 43, 50, 4, true); // 炮筒
                g.fill3DRect(x - 35, y + 41, 20, 8, true); // 炮筒头
                break;
            // 向右的坦克
            case RIGHT:
                g.fill3DRect(x - 15, y + 15, 90, 15, true); // 左轮子
                g.fill3DRect(x - 15, y + 60, 90, 15, true); // 右轮子
                g.fill3DRect(x, y + 30, 60, 30, true); // 中间部分
                g.setColor(Color.gray);
                g.fillOval(x + 15, y + 30, 30, 30); // 顶
                g.fill3DRect(x + 45, y + 43, 50, 4, true); // 炮筒
                g.fill3DRect(x + 75, y + 41, 20, 8, true); // 炮筒头
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        MyTank mt = (MyTank) myTank;

        // 向上移动
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            mt.setDirection(Direction.UP);
            mt.setMoveUp(true);
            mt.setMoveDown(false);
            mt.setMoveLeft(false);
            mt.setMoveRight(false);
            // 向下移动
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            mt.setDirection(Direction.DOWN);
            mt.setMoveUp(false);
            mt.setMoveDown(true);
            mt.setMoveLeft(false);
            mt.setMoveRight(false);
            // 向左移动
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            mt.setDirection(Direction.LEFT);
            mt.setMoveUp(false);
            mt.setMoveDown(false);
            mt.setMoveLeft(true);
            mt.setMoveRight(false);
            // 向右移动
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            mt.setDirection(Direction.RIGHT);
            mt.setMoveUp(false);
            mt.setMoveDown(false);
            mt.setMoveLeft(false);
            mt.setMoveRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        MyTank mt = (MyTank) myTank;

        // 发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
            Shot shot = mt.shot();
            mt.getShots().add(shot);
            new Thread(shot).start();
        }
        // 停止向上移动
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            mt.setMoveUp(false);
            // 停止向下移动
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            mt.setMoveDown(false);
            // 停止向左移动
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            mt.setMoveLeft(false);
            // 停止向右移动
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            mt.setMoveRight(false);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            MyTank mt = (MyTank) myTank;

            new Thread(mt).start();

            // 判断我方坦克是否击中敌方坦克
            for (int i = 0; i < mt.getShots().size(); i++) {
                Shot shot = mt.getShots().get(i);
                if (shot.isLive()) {
                    for (int j = 0; j < enemyTanks.size(); j++) {
                        Tank enemyTank = enemyTanks.get(j);
                        Tank.hitTank(shot, enemyTank);
                    }
                }
            }

            this.repaint();
        }
    }

    public static Tank getMyTank() {
        return myTank;
    }

    public static Vector<Shot> getShots() {
        return shots;
    }
}
