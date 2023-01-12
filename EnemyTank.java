package tankbattle;

public class EnemyTank extends Tank implements Runnable {
    
    public EnemyTank(int x, int y) {
        super(x, y);
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
                    if (getY() > 2) moveUp();
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
                    if (getY() < 710) moveDown();
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
                    if (getX() > 7) moveLeft();
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
                    if (getX() < 937) moveRight();
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
}
