package tankbattle;

public class Shot implements Runnable {
    private int x;
    private int y;
    private int speed = 1;
    private Direction direction;
    private boolean isLive = true;

    public Shot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direction) {
                case UP: // 向上发射子弹
                    y -= speed;
                    break;
                case DOWN: // 向下发射子弹
                    y += speed;
                    break;
                case LEFT: // 向左发射子弹
                    x -= speed;
                    break;
                case RIGHT: // 向右发射子弹
                    x += speed;
                    break;
            }
            if (!(x >= 0 && x <= 1200 && y >= 0 && y <= 945 && isLive)) {
                isLive = false;
                if (((MyTank) MyPanel.getMyTank()).getShots().contains(this)) {
                    ((MyTank) MyPanel.getMyTank()).getShots().remove(this);
                } else if (MyPanel.getShots().contains(this)) {
                    MyPanel.getShots().remove(this);
                }
                break;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
