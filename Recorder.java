package tankbattle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * 用于记录相关信息和文件交互
 */
public class Recorder {
    private static int hitEnemyTankNum = 0;
    private static BufferedWriter bufferedWriter = null;
    private static BufferedReader bufferedReader = null;
    private static String recordDirectory = "tankbattle/GameTemp";
    private static String recordFilePath = "tankbattle/GameTemp/Record.txt";
    private static MyTank myTank = null;
    private static Vector<EnemyTank> enemyTanks = null;
    private static Vector<Node> nodes = new Vector<>();

    public static Vector<Node> getNodesAndHitEnemyTankNum() {
        try {
            bufferedReader = new BufferedReader(new FileReader(recordFilePath));
            // 读取我方坦克的信息，并将其存入到nodes集合中
            String[] myXYD = bufferedReader.readLine().split(" ");
            nodes.add(new Node(Integer.parseInt(myXYD[0]), Integer.parseInt(myXYD[1]), myXYD[2]));
            // 读取击中敌方坦克数量，赋给hitEnemyTankNum
            hitEnemyTankNum = Integer.parseInt(bufferedReader.readLine());
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] enemyXYD = line.split(" ");
                Node node = new Node(Integer.parseInt(enemyXYD[0]), Integer.parseInt(enemyXYD[1]), enemyXYD[2]);
                nodes.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nodes;
    }

    /**
     * 当游戏退出时，将记录保存到recordFilePath
     */
    public static void saveRecord() {
        if (myTank.isLive()) {
            File file = new File(recordDirectory);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(recordFilePath));
                bufferedWriter.write(myTank.getX() + " " + myTank.getY() + " " + myTank.getDirection().getDirection());
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(hitEnemyTankNum));
                bufferedWriter.newLine();
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank.isLive()) {
                        bufferedWriter.write(enemyTank.getX() + " "
                                + enemyTank.getY() + " " + enemyTank.getDirection().getDirection());
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

    // 清空记录文件
    public static void writeEmpty() {
        if(new File(recordFilePath).exists()) {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(recordFilePath));
                bufferedWriter.write("");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
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

    public static boolean isEmpty() {
        try {
            if (!(new File(recordFilePath).exists())
                    || (bufferedReader = new BufferedReader(new FileReader(recordFilePath))).readLine() == null) {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}