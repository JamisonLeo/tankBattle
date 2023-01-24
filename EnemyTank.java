package tankbattle;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {

    private Vector<EnemyTank> enemyTanks = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    /**
     * 判断敌方坦克是否重叠
     */
    public boolean isTouchEnemyTank() {
        switch (this.getDirection()) {
            case UP:
                // 让当前坦克和其他所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 不与自己比较
                    if (enemyTank != this) {
                        // 根据其他坦克的方向进行判断是否重叠
                        switch (enemyTank.getDirection()) {
                            case UP:
                            case DOWN:
                                // 判断当前坦克的左上角是否在目标坦克内
                                if (this.getX() >= enemyTank.getX()
                                        && this.getX() <= enemyTank.getX() + 40
                                        && this.getY() >= enemyTank.getY()
                                        && this.getY() <= enemyTank.getY() + 50) {
                                    return true;
                                }
                                // 判断当前坦克的右上角是否在目标坦克内
                                if (this.getX() + 40 >= enemyTank.getX()
                                        && this.getX() + 40 <= enemyTank.getX() + 40
                                        && this.getY() >= enemyTank.getY()
                                        && this.getY() <= enemyTank.getY() +50) {
                                    return true;
                                }
                                break;
                            case LEFT:
                            case RIGHT:
                                // 判断当前坦克的左上角是否在目标坦克内
                                if (this.getX() >= enemyTank.getX() - 5
                                        && this.getX() <= enemyTank.getX() + 45
                                        && this.getY() >= enemyTank.getY() + 5
                                        && this.getY() <= enemyTank.getY() + 45) {
                                    return true;
                                }
                                // 判断当前坦克的右上角是否在目标坦克内
                                if (this.getX() + 40 >= enemyTank.getX() - 5
                                        && this.getX() + 40 <= enemyTank.getX() + 45
                                        && this.getY() >= enemyTank.getY() + 5
                                        && this.getY() <= enemyTank.getY() + 45) {
                                    return true;
                                }
                                break;
                        }
                    }
                }
                break;
            case DOWN:
                // 让当前坦克和其他所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 不与自己比较
                    if (enemyTank != this) {
                        // 根据其他坦克的方向进行判断是否重叠
                        switch (enemyTank.getDirection()) {
                            case UP:
                            case DOWN:
                                // 判断当前坦克的左下角是否在目标坦克内
                                if (this.getX() >= enemyTank.getX()
                                        && this.getX() <= enemyTank.getX() + 40
                                        && this.getY() + 50 >= enemyTank.getY()
                                        && this.getY() + 50 <= enemyTank.getY() + 50) {
                                    return true;
                                }
                                // 判断当前坦克的右下角是否在目标坦克内
                                if (this.getX() + 40 >= enemyTank.getX()
                                        && this.getX() + 40 <= enemyTank.getX() + 40
                                        && this.getY() + 50 >= enemyTank.getY()
                                        && this.getY() + 50 <= enemyTank.getY() +50) {
                                    return true;
                                }
                                break;
                            case LEFT:
                            case RIGHT:
                                // 判断当前坦克的左下角是否在目标坦克内
                                if (this.getX() >= enemyTank.getX() - 5
                                        && this.getX() <= enemyTank.getX() + 45
                                        && this.getY() + 50 >= enemyTank.getY() + 5
                                        && this.getY() + 50 <= enemyTank.getY() + 45) {
                                    return true;
                                }
                                // 判断当前坦克的右下角是否在目标坦克内
                                if (this.getX() + 40 >= enemyTank.getX() - 5
                                        && this.getX() + 40 <= enemyTank.getX() + 45
                                        && this.getY() + 50 >= enemyTank.getY() + 5
                                        && this.getY() + 50 <= enemyTank.getY() + 45) {
                                    return true;
                                }
                                break;
                        }
                    }
                }
                break;
            case LEFT:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 不与自己比较
                    if (enemyTank != this) {
                        // 根据其他坦克的方向进行判断是否重叠
                        switch (enemyTank.getDirection()) {
                            case UP:
                            case DOWN:
                                // 判断当前坦克的左上角是否在目标坦克内
                                if (this.getX() - 5 >= enemyTank.getX()
                                        && this.getX() - 5 <= enemyTank.getX() + 40
                                        && this.getY() + 5 >= enemyTank.getY()
                                        && this.getY() + 5 <= enemyTank.getY() + 50) {
                                    return true;
                                }
                                // 判断当前坦克的左下角是否在目标坦克内
                                if (this.getX() - 5 >= enemyTank.getX()
                                        && this.getX() - 5 <= enemyTank.getX() + 40
                                        && this.getY() + 45 >= enemyTank.getY()
                                        && this.getY() + 45 <= enemyTank.getY() + 50) {
                                    return true;
                                }
                                break;
                            case LEFT:
                            case RIGHT:
                                // 判断当前坦克的左上角是否在目标坦克内
                                if (this.getX() - 5 >= enemyTank.getX() - 5
                                        && this.getX() - 5 <= enemyTank.getX() + 45
                                        && this.getY() + 5 >= enemyTank.getY() + 5
                                        && this.getY() + 5 <= enemyTank.getY() + 45) {
                                    return true;
                                }
                                // 判断当前坦克的左下角是否在目标坦克内
                                if (this.getX() - 5 >= enemyTank.getX() - 5
                                        && this.getX() - 5 <= enemyTank.getX() + 45
                                        && this.getY() + 45 >= enemyTank.getY() + 5
                                        && this.getY() + 45 <= enemyTank.getY() + 45) {
                                    return true;
                                }
                                break;
                        }
                    }
                }
                break;
            case RIGHT:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 不与自己比较
                    if (enemyTank != this) {
                        // 根据其他坦克的方向进行判断是否重叠
                        switch (enemyTank.getDirection()) {
                            case UP:
                            case DOWN:
                                // 判断当前坦克的右上角是否在目标坦克内
                                if (this.getX() + 45 >= enemyTank.getX()
                                        && this.getX() + 45 <= enemyTank.getX() + 40
                                        && this.getY() + 5 >= enemyTank.getY()
                                        && this.getY() + 5 <= enemyTank.getY() + 50) {
                                    return true;
                                }
                                // 判断当前坦克的右下角是否在目标坦克内
                                if (this.getX() + 45 >= enemyTank.getX()
                                        && this.getX() + 45 <= enemyTank.getX() + 40
                                        && this.getY() + 45 >= enemyTank.getY()
                                        && this.getY() + 45 <= enemyTank.getY() + 50) {
                                    return true;
                                }
                                break;
                            case LEFT:
                            case RIGHT:
                                // 判断当前坦克的右上角是否在目标坦克内
                                if (this.getX() + 45 >= enemyTank.getX() - 5
                                        && this.getX() + 45 <= enemyTank.getX() + 45
                                        && this.getY() + 5 >= enemyTank.getY() + 5
                                        && this.getY() + 5 <= enemyTank.getY() + 45) {
                                    return true;
                                }
                                // 判断当前坦克的右下角是否在目标坦克内
                                if (this.getX() + 45 >= enemyTank.getX() - 5
                                        && this.getX() + 45 <= enemyTank.getX() + 45
                                        && this.getY() + 45 >= enemyTank.getY() + 5
                                        && this.getY() + 45 <= enemyTank.getY() + 45) {
                                    return true;
                                }
                                break;
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            if (isLive() && MyPanel.getShots().size() < 3) {
                if(((int)(Math.random() * 10 + 1)) <= 7) {
                    Shot shot = shot();
                    MyPanel.getShots().add(shot);
                    new Thread(shot).start();
                }
            }

            switch (getDirection()) {
                case UP:
                    for (int i = 0; i < 70; i++){
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (isTouchEnemyTank()) {
                            i += 10;
                            continue;
                        }
                        if (getY() > 2 && !isTouchEnemyTank()) moveUp();
                        else break;
                    }
                    break;
                case DOWN:
                    for (int i = 0; i < 70; i++){
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (isTouchEnemyTank()) {
                            i += 10;
                            continue;
                        }
                        if (getY() < 710 && !isTouchEnemyTank()) moveDown();
                        else break;
                    }
                    break;
                case LEFT:
                    for (int i = 0; i < 70; i++){
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (isTouchEnemyTank()) {
                            i += 10;
                            continue;
                        }
                        if (getX() > 7 && !isTouchEnemyTank()) moveLeft();
                        else break;
                    }
                    break;
                case RIGHT:
                    for (int i = 0; i < 70; i++){
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (isTouchEnemyTank()) {
                            i += 10;
                            continue;
                        }
                        if (getX() < 937 && !isTouchEnemyTank()) moveRight();
                        else break;
                    }
                    break;
            }

            switch ((int)(Math.random() * 4)) {
                case 0:
                    setDirection(Direction.UP);
                    break;
                case 1:
                    setDirection(Direction.DOWN);
                    break;
                case 2:
                    setDirection(Direction.LEFT);
                    break;
                case 3:
                    setDirection(Direction.RIGHT);
                    break;
            }
        }
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }
}
