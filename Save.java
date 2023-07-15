package SpaceInvaders;

import java.io.*;
import java.util.Vector;
@SuppressWarnings({"all"})
public class Save {
    private static int allInvader = 0;
    private static int heroLife = Hero.life;
    private static int bulletNum = Hero.bulletNum;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "D:\\save.txt";
    private static Vector<Invaders> invadersVector = new Vector<>();
    private static Vector<Barrier> barrierVector = new Vector<>();
    private static Vector<Invaders> nodeVectorInvaders = new Vector<Invaders>();
    private static Vector<Barrier> nodeVectorBarrier = new Vector<Barrier>();

    public static int getBulletNum() {
        return bulletNum;
    }

    public static void setBulletNum(int bulletNum) {
        Save.bulletNum = bulletNum;
    }

    public static void allInvader(){
        Save.allInvader+=50;
    }
    public static void heroLife(){
        Save.heroLife--;
    }

    public static int getAllInvader() {
        return allInvader;
    }

    public static void setAllInvader(int allInvader) {
        Save.allInvader = allInvader;
    }

    public static String getRecordFile() {
        return recordFile;
    }

    public static void setRecordFile(String recordFile) {
        Save.recordFile = recordFile;
    }

    public static Vector<Invaders> getInvadersVector() {
        return invadersVector;
    }

    public static void setInvadersVector(Vector<Invaders> invadersVector) {
        Save.invadersVector = invadersVector;
    }

    public static Vector<Barrier> getBarrierVector() {
        return barrierVector;
    }

    public static void setBarrierVector(Vector<Barrier> barrierVector) {
        Save.barrierVector = barrierVector;
    }

    public static Vector<Invaders> getNodeVector() {
        return nodeVectorInvaders;
    }

    public static void setNodeVector(Vector<Invaders> nodeVectorInvaders) {
        Save.nodeVectorInvaders = nodeVectorInvaders;
    }

    public static int getHeroLife() {
        return heroLife;
    }

    public static void setHeroLife(int heroLife) {
        Save.heroLife = heroLife;
    }

    public static Vector<Invaders> getInvadersAndallInvader() {
    try {
        br = new BufferedReader(new FileReader(recordFile));
        allInvader = Integer.parseInt(br.readLine());
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] xyd = line.split(" ");
            if (xyd[0].equals("222")) {
                break;
            }
            Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]),Integer.parseInt(xyd[2]));
            if (xyd[3].equals("L")) {
                invadersVector.add(new BlueInvaders(node.getX(), node.getY(), node.getxSpeed()));
            } else if (xyd[3].equals("G")) {
                invadersVector.add(new GreenInvaders(node.getX(), node.getY(), node.getxSpeed()));
            } else if (xyd[3].equals("R")) {
                invadersVector.add(new Invaders(node.getX(), node.getY(), node.getxSpeed()));
            } else if (xyd[3].equals("B")) {
                break;
            }
        }
        nodeVectorInvaders.addAll(invadersVector);
    } catch (IOException e) {
        throw new RuntimeException(e);
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    return nodeVectorInvaders;
}
    public static Vector<Barrier> getBarrierandHeroLife() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            br.readLine();
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                if (xyd[1].equals("I")) {
                    heroLife = Integer.parseInt(xyd[0]);
                    line = br.readLine();
                    String[] xyd1 = line.split(" ");
                    bulletNum = Integer.parseInt(xyd1[0]);
                    break;
                }
                Node nodes = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]));
                if (xyd[2].equals("B")) {
                    barrierVector.add(new Barrier(nodes.getX(), nodes.getY()));
                }
            }
            nodeVectorBarrier.addAll(barrierVector);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodeVectorBarrier;
    }
public static void saveInfo() {
    try {
        bw = new BufferedWriter(new FileWriter(recordFile));
        bw.write(String.valueOf(allInvader));
        bw.newLine();
        //遍历敌人和墙的Vector,然后根据情况保存即可
        for (int i = 0; i < invadersVector.size(); i++) {
            Invaders invader = invadersVector.get(i);
            if (invader.isIslive()) {
                if (invader instanceof BlueInvaders) {
                    //保存下来
                    String record = invader.getX() + " " + invader.getY() + " " +  invader.getxSpeed() + " L";
                    //写入文件
                    bw.write(record);
                    bw.newLine();
                } else if (invader instanceof GreenInvaders) {
                    //保存下来
                    String record = invader.getX() + " " + invader.getY() + " " +  invader.getxSpeed() + " G";
                    //写入文件
                    bw.write(record);
                    bw.newLine();
                } else {
                    //保存下来
                    String record = invader.getX() + " " + invader.getY() + " " +  invader.getxSpeed() + " R";
                    //写入文件
                    bw.write(record);
                    bw.newLine();
                }
            }
        }
        bw.write("222"+" 8424"+" 054");//
        bw.newLine();
        for (int i = 0; i < barrierVector.size(); i++) {
            Barrier barrier = barrierVector.get(i);
            if (barrier.islive) {
                String record = barrier.getX() + " " + barrier.getY() + " B";
                bw.write(record);
                bw.newLine();
            }
        }
        bw.write(String.valueOf(heroLife)+" I");
        bw.newLine();
        bw.write(String.valueOf(Hero.bulletNum)+" N");
        bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}