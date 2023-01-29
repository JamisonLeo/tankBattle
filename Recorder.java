package tankbattle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 用于记录相关信息和文件交互
 */
public class Recorder {
    private static int hitEnemyTankNum = 0;
    private static BufferedWriter bufferedWriter = null;
    private static String recordFilePath = "hitEnemyTankNum.txt";

    /**
     * 当游戏退出时，将hitEnemyTankNum保存到recordFilePath
     */
    public static void saveRecord() {
        if (hitEnemyTankNum != 0) {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(recordFilePath));
                bufferedWriter.write(String.valueOf(hitEnemyTankNum));
                bufferedWriter.newLine();
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
}