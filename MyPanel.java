package tankbattle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    // 我方坦克
    static MyTank myTank = null;
    // 敌方坦克集合
    static Vector<EnemyTank> enemyTanks = new Vector<>();
    // 敌方坦克数量
    int enemyTankSize = 3;
    // 敌方坦克子弹集合
    static Vector<Shot> shots = new Vector<>();
    // 定义一个Vector，用来存放爆炸
    static Vector<Bomb> bombs = new Vector<>();
    // 定义三张图片，用于显示爆炸效果
    Image bomb1Image = null;
    Image bomb2Image = null;
    Image bomb3Image = null;
    // 定义八张图片，分别显示双方四个方向的坦克
    Image myTankUpImage = null;
    Image myTankDownImage = null;
    Image myTankLeftImage = null;
    Image myTankRightImage = null;
    Image enemyTankUpImage = null;
    Image enemyTankDownImage = null;
    Image enemyTankLeftImage = null;
    Image enemyTankRightImage = null;

    public MyPanel(int key) {
        // 将enemyTanks设置给Recorder.enemyTanks用于结束时记录敌方坦克的信息
        Recorder.setEnemyTanks(enemyTanks);

        switch (key) {
            // 新游戏
            case 1:
                Recorder.writeEmpty();
                // 创建我方坦克
                myTank = new MyTank(100, 100);
                myTank.setDirection(Direction.DOWN);
                Recorder.setMyTank(myTank);

                // 创建敌方坦克
                for (int i = 0; i < enemyTankSize; i++) {
                    // 创建一个敌方坦克，并启动线程
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 700);
                    enemyTank.setEnemyTanks(enemyTanks);
                    new Thread(enemyTank).start();

                    // 加入
                    enemyTanks.add(enemyTank);
                }
                break;
            // 继续游戏
            case 2:
                // 恢复坦克集合
                Vector<Node> nodes = Recorder.getNodesAndHitEnemyTankNum();
                // 创建我方坦克
                Node myTankNode = nodes.get(0);
                myTank = new MyTank(myTankNode.getX(), myTankNode.getY());
                switch (myTankNode.getDirection()) {
                    case "上":
                        myTank.setDirection(Direction.UP);
                        break;
                    case "下":
                        myTank.setDirection(Direction.DOWN);
                        break;
                    case "左":
                        myTank.setDirection(Direction.LEFT);
                        break;
                    case "右":
                        myTank.setDirection(Direction.RIGHT);
                        break;
                }
                Recorder.setMyTank(myTank);

                // 创建敌方坦克
                for (int i = 1; i < nodes.size(); i++) {
                    Node enemyTankNode = nodes.get(i);
                    // 创建一个敌方坦克，并启动线程
                    EnemyTank enemyTank = new EnemyTank(enemyTankNode.getX(), enemyTankNode.getY());
                    switch (enemyTankNode.getDirection()) {
                        case "上":
                            enemyTank.setDirection(Direction.UP);
                            break;
                        case "下":
                            enemyTank.setDirection(Direction.DOWN);
                            break;
                        case "左":
                            enemyTank.setDirection(Direction.LEFT);
                            break;
                        case "右":
                            enemyTank.setDirection(Direction.RIGHT);
                            break;
                    }
                    enemyTank.setEnemyTanks(enemyTanks);
                    new Thread(enemyTank).start();

                    // 加入
                    enemyTanks.add(enemyTank);
                }
                break;
        }
        // 初始化坦克图片
        myTankUpImage = Toolkit.getDefaultToolkit()
                .getImage(MyPanel.class.getResource("/tankBattle/file/mytank_up.png"));
        myTankDownImage = Toolkit.getDefaultToolkit()
                .getImage(MyPanel.class.getResource("/tankBattle/file/mytank_down.png"));
        myTankLeftImage = Toolkit.getDefaultToolkit()
                .getImage(MyPanel.class.getResource("/tankBattle/file/mytank_left.png"));
        myTankRightImage = Toolkit.getDefaultToolkit()
                .getImage(MyPanel.class.getResource("/tankBattle/file/mytank_right.png"));
        enemyTankUpImage = Toolkit.getDefaultToolkit()
                .getImage(MyPanel.class.getResource("/tankBattle/file/enemytank_up.png"));
        enemyTankDownImage = Toolkit.getDefaultToolkit()
                .getImage(MyPanel.class.getResource("/tankBattle/file/enemytank_down.png"));
        enemyTankLeftImage = Toolkit.getDefaultToolkit()
                .getImage(MyPanel.class.getResource("/tankBattle/file/enemytank_left.png"));
        enemyTankRightImage = Toolkit.getDefaultToolkit()
                .getImage(MyPanel.class.getResource("/tankBattle/file/enemytank_right.png"));

        // 初始化爆炸效果的图片
        bomb1Image = Toolkit.getDefaultToolkit()
                .getImage(MyPanel.class.getResource("/tankBattle/file/bomb_1.png"));
        bomb2Image = Toolkit.getDefaultToolkit()
                .getImage(MyPanel.class.getResource("/tankBattle/file/bomb_2.png"));
        bomb3Image = Toolkit.getDefaultToolkit()
                .getImage(MyPanel.class.getResource("/tankBattle/file/bomb_3.png"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(3, 0, 980, 760);
        showInfo(g);

        // 画出我方坦克
        if (myTank != null && myTank.isLive()) {
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirection(), 0);
        }

        // 画出我方坦克子弹
        drawShots(g, ((MyTank) myTank).getShots());

        // 画出爆炸效果
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.getLife() > 27)
                g.drawImage(bomb1Image, bomb.getX(), bomb.getY(), 40, 40, this);
            else if (bomb.getLife() > 18)
                g.drawImage(bomb2Image, bomb.getX(), bomb.getY(), 40, 40, this);
            else
                g.drawImage(bomb3Image, bomb.getX(), bomb.getY(), 40, 40, this);
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
     * 画出坦克
     *
     * @param x         横坐标
     * @param y         纵坐标
     * @param g         画笔
     * @param direction 坦克方向
     * @param type      坦克阵营(0-我防坦克， 1-敌方坦克)
     */
    public void drawTank(int x, int y, Graphics g, Direction direction, int type) {
        switch (direction) {
            case UP:
                if (type == 0)
                    g.drawImage(myTankUpImage, x, y, 40, 50, this);
                if (type == 1)
                    g.drawImage(enemyTankUpImage, x, y, 40, 50, this);
                break;
            case DOWN:
                if (type == 0)
                    g.drawImage(myTankDownImage, x, y, 40, 50, this);
                if (type == 1)
                    g.drawImage(enemyTankDownImage, x, y, 40, 50, this);
                break;
            case LEFT:
                if (type == 0)
                    g.drawImage(myTankLeftImage, x - 5, y + 5, 50, 40, this);
                if (type == 1)
                    g.drawImage(enemyTankLeftImage, x - 5, y + 5, 50, 40, this);
                break;
            case RIGHT:
                if (type == 0)
                    g.drawImage(myTankRightImage, x - 5, y + 5, 50, 40, this);
                if (type == 1)
                    g.drawImage(enemyTankRightImage, x - 5, y + 5, 50, 40, this);
                break;
        }
    }

    /**
     * 画出坦克所有的子弹
     *
     * @param g     画笔
     * @param shots 该坦克的子弹集合
     */
    public void drawShots(Graphics g, Vector<Shot> shots) {
        for (int i = 0; i < shots.size(); i++) {
            Shot shot = shots.get(i);
            if (shot.isLive()) {
                g.setColor(Color.white);
                g.fillOval(shot.getX(), shot.getY(), 8, 8);
            }
        }
    }

    /**
     * 在界面右侧画出统计信息
     */
    public void showInfo(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("仿宋", Font.BOLD, 25));
        g.drawString("累计击中敌方坦克", 985, 50);
        drawTank(1045, 80, g, Direction.UP, 1);
        g.setFont(new Font("黑体", Font.BOLD, 35));
        g.drawString(String.valueOf(Recorder.getHitEnemyTankNum()), 1120, 120);
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

    /** 判断是否有坦克被击中 */
    class IsHit extends Thread {
        @Override
        public void run() {
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

            // 判断敌方坦克是否击中我方坦克
            for (int i = 0; i < shots.size(); i++) {
                Shot shot = getShots().get(i);
                if (shot.isLive()) {
                    Tank.hitTank(shot, mt);
                }
            }
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

            new IsHit().start();

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
