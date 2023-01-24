package tankbattle;

public class Tank {
    private int x;
    private int y;
    private Direction direction = Direction.UP;
    private boolean isLive = true;
    private int speed = 1;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Shot shot() {
        Shot shot = null;
        switch (getDirection()) {
            case UP:
                shot = new Shot(getX() + 16, getY() - 8, Direction.UP);
                return shot;
            case DOWN:
                shot = new Shot(getX() + 16, getY() + 50, Direction.DOWN);
                return shot;
            case LEFT:
                shot = new Shot(getX() - 13, getY() + 21, Direction.LEFT);
                return shot;
            case RIGHT:
                shot = new Shot(getX() + 45, getY() + 21, Direction.RIGHT);
                return shot;
        }
        return null;
    }

    /**
     * 判断子弹是否击中敌方坦克
     *
     * @param shot 子弹
     * @param tank 坦克
     */
    public static void hitTank(Shot shot, Tank tank) {
        switch (tank.direction) {
            case UP:
            case DOWN:
                if (shot.getX() + 8 > tank.x && shot.getX() < tank.x + 50
                        && shot.getY() + 8 > tank.y && shot.getY() < tank.y + 40) {
                    shot.setLive(false);
                    tank.setLive(false);
                    if (tank instanceof EnemyTank) {
                        MyPanel.enemyTanks.remove((EnemyTank) tank);
                    }
                    // 创建Bomb对象，加入到MyPanel的bombs集合中
                    Bomb bomb = new Bomb(tank.x, tank.y);
                    MyPanel.bombs.add(bomb);
                }
                break;
            case LEFT:
            case RIGHT:
                if (shot.getX() + 8 > tank.x - 5 && shot.getX() < tank.x + 45
                        && shot.getY() + 8 > tank.y + 5 && shot.getY() < tank.y + 45) {
                    shot.setLive(false);
                    tank.setLive(false);
                    if (tank instanceof EnemyTank) {
                        MyPanel.enemyTanks.remove((EnemyTank) tank);
                    }
                    // 创建Bomb对象，加入到MyPanel的bombs集合中
                    Bomb bomb = new Bomb(tank.x, tank.y);
                    MyPanel.bombs.add(bomb);
                }
                break;
        }

    }

    public void moveUp() {
        y -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
