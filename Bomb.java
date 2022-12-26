package tankbattle;

public class Bomb {
    private int x; // 爆炸的横坐标
    private int y; // 爆炸的纵坐标
    private int life = 36; // 爆炸的声明周期
    private boolean isLive = true; // 爆炸是否存在
    
    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * 减少爆炸的生命周期
     */
    public void subLife() {
        if (life > 0) life--;
        else isLive = false;
    }
    
    public int getLife() {
        return life;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public boolean isLive() {
        return isLive;
    }
    
    public void setLive(boolean live) {
        isLive = live;
    }
}
