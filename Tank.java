package tankbattle;

public class Tank {
    private int x;
    private int y;
    private Direction direction = Direction.UP;
    private boolean isLive = true;
    private int speed = 3;
    
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Shot shot() {
        Shot shot = null;
        switch (getDirection()) {
            case UP:
                shot = new Shot(getX() + 26, getY() - 26, Direction.UP);
                return shot;
            case DOWN:
                shot = new Shot(getX() + 26, getY() + 110, Direction.DOWN);
                return shot;
            case LEFT:
                shot = new Shot(getX() - 40, getY() + 41, Direction.LEFT);
                return shot;
            case RIGHT:
                shot = new Shot(getX() + 95, getY() + 41, Direction.RIGHT);
                return shot;
        }
        return null;
    }

    /**
     * 判断子弹是否击中敌方坦克
     * @param shot  子弹
     * @param tank  坦克
     */
    public static void hitTank(Shot shot, Tank tank) {
        switch (tank.getDirection()) {
            case UP:
            case DOWN:
                if (shot.getX() + 8 > tank.getX() && shot.getX() < tank.getX() + 60
                        && shot.getY() + 8 > tank.getY() && shot.getY() < tank.getY() + 90) {
                    shot.setLive(false);
                    tank.setLive(false);
                    if(tank instanceof EnemyTank) {
                        MyPanel.enemyTanks.remove((EnemyTank)tank);
                    }
                    // 创建Bomb对象，加入到 MyPanel 的bombs集合中
                    Bomb bomb = new Bomb(tank.getX() - 15, tank.getY());
                    MyPanel.bombs.add(bomb);
                }
                break;
            case LEFT:
            case RIGHT:
                if (shot.getX() + 8 > tank.getX() - 15 && shot.getX() < tank.getX() + 75
                        && shot.getY() + 8 > tank.getY() + 15 && shot.getY() < tank.getY() + 75) {
                    shot.setLive(false);
                    tank.setLive(false);
                    if(tank instanceof EnemyTank) {
                        MyPanel.enemyTanks.remove((EnemyTank)tank);
                    }
                    // 创建Bomb对象，加入到 MyPanel 的bombs集合中
                    Bomb bomb = new Bomb(tank.getX() - 15, tank.getY());
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
