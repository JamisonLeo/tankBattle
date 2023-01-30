package tankbattle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * 用于记录相关信息和文件交互
 */
public class Recorder {
    private static int hitEnemyTankNum = 0;
    private static BufferedWriter bufferedWriter = null;
    private static String recordDirectory = "tankbattle/GameTemp";
    private static String recordFilePath = "tankbattle/GameTemp/Record.txt";
    private static MyTank myTank = null;
    private static Vector<EnemyTank> enemyTanks = null;

    /**
     * 当游戏退出时，将hitEnemyTankNum保存到recordFilePath
     */
    public static void saveRecord() {
        if(myTank.isLive()) {
            File file = new File(recordDirectory);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(recordFilePath));
                bufferedWriter.write(myTank.getX() + " " + myTank.getY() + " " + myTank.getDirection());
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(hitEnemyTankNum));
                bufferedWriter.newLine();
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank.isLive()) {
                        bufferedWriter.write(enemyTank.getX() + " "
                                + enemyTank.getY() + " " + enemyTank.getDirection());
                        bufferedWriter.newLine();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static int getHitEnemyTankNum() {
        return hitEnemyTankNum;
    }

    public static void setHitEnemyTankNum(int hitEnemyTankNum) {
        Recorder.hitEnemyTankNum = hitEnemyTankNum;
    }

    public static void addHitEnemyTankNum() {
        ++Recorder.hitEnemyTankNum;
    }

    public static void setMyTank(MyTank myTank) {
        Recorder.myTank = myTank;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }
}