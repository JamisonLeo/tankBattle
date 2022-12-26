package tankbattle;

import java.util.Vector;

public class MyTank extends Tank implements Runnable {
    
    private boolean moveUp = false;
    private boolean moveRight = false;
    private boolean moveDown = false;
    private boolean moveLeft = false;
    private Vector<Shot> shots = new Vector<>();
    
    public MyTank(int x, int y) {
        super(x, y);
    }
    
    public boolean isMoveUp() {
        return moveUp;
    }
    
    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }
    
    public boolean isMoveRight() {
        return moveRight;
    }
    
    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }
    
    public boolean isMoveDown() {
        return moveDown;
    }
    
    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }
    
    public boolean isMoveLeft() {
        return moveLeft;
    }
    
    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public Vector<Shot> getShots() {
        return shots;
    }
    
    /**
     * 坦克的移动
     */
    @Override
    public void run() {
        if (isMoveUp() && !isMoveDown() && !isMoveLeft() && !isMoveRight()) {
            if (getY() > 20) moveUp();
        } else if (!isMoveUp() && isMoveDown() && !isMoveLeft() && !isMoveRight()) {
            if (getY() < 830) moveDown();
        } else if (!isMoveUp() && !isMoveDown() && isMoveLeft() && !isMoveRight()) {
            if (getX() > 40) moveLeft();
        } else if (!isMoveUp() && !isMoveDown() && !isMoveLeft() && isMoveRight()) {
            if (getX() < 1106) moveRight();
        }
    }
}
