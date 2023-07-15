package SpaceInvaders;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;
import java.util.Vector;
@SuppressWarnings({"all"})//抑制，美观，嗯~ o(*￣▽￣*)o
public class Hero {//长·60 宽。40
    private int x;//横坐标
    private int y;//纵坐标
    private int speed=10;
    boolean islive = true;
    Bullet bullet = null;
    Vector<Bullet> bullets = new Vector<>();
    private int type = 0;
    public static int life =3;
    public static int bulletNum = 5;

    public int getBulletNum() {
        return bulletNum;
    }

    public void setBulletNum(int bulletNum) {
        this.bulletNum = bulletNum;
    }

    public void reduceLife() {
        life--;
    }
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        life = life;
    }


    public void moveRight(){
        x+=speed;
    }
    public void moveLeft(){
        x-=speed;
    }

    public Hero(int x, int y, int speed,int life,int bulletNum) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.life = life;
        this.bulletNum = bulletNum;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isIslive() {
        return islive;
    }

    public void setIslive(boolean islive) {
        this.islive = islive;
    }
    public void drawHero(int x, int y, Graphics g){

        switch (type){
            case 0://
                g.setColor(Color.gray);
                int[] xpoints1 = {x+20, x+15 ,x,    x+40, x+25};
                int[] ypoints1 = {y,    y+20 ,y+40, y+40, y+20};
                g.fillPolygon(xpoints1, ypoints1, 5);
                g.setColor(Color.red);
                int[] xpoints2 = {x+13, x+27, x+20};
                int[] ypoints2 = {y+38, y+38, y+50};
                g.fillPolygon(xpoints2, ypoints2, 3);
                g.setColor(Color.darkGray);
                g.fill3DRect(x+18,y+30,4,8,false);
                g.setColor(Color.blue);
                g.fillOval(x+17,y+18,5,10);//圆盘
                g.setColor(Color.RED);
                break;
            case 1://
                g.setColor(Color.gray);
                int[] xpoints3 = {x+20, x+15 ,x,    x+40, x+25};
                int[] ypoints3 = {y,    y+20 ,y+40, y+40, y+20};
                g.fillPolygon(xpoints3, ypoints3, 5);
                g.setColor(Color.red);
                int[] xpoints4 = {x+13, x+27, x+20};
                int[] ypoints4 = {y+38, y+38, y+50};
                g.fillPolygon(xpoints4, ypoints4, 3);
                g.setColor(Color.darkGray);
                g.fill3DRect(x+18,y+30,4,8,false);
                g.setColor(Color.blue);
                g.fillOval(x+17,y+18,5,10);//圆盘
                g.setColor(Color.orange);
                int[] xpoints5 = {x+16, x+24, x+20};
                int[] ypoints5 = {y+38, y+38, y+45};
                g.fillPolygon(xpoints5, ypoints5, 3);
                g.setColor(Color.RED);
                break;
        }
        if (type == 0) {
            type++;
        }else {
            type--;
        }
    }
    public void playSoundEffect(String soundFilePath) {
        try {
            // 加载音效文件
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFilePath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // 播放音效文件
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error playing sound effect: " + ex.getMessage());
        }
    }
    public void shotInvader(){
        //控制子弹数量
        if (bullets.size() == bulletNum) {
            return;
        }
        //创建bullet
        bullet = new Bullet(getX()+17,getY(),20,0);
        playSoundEffect("C:\\Users\\86158\\IdeaProjects\\javacode\\src\\SpaceInvaders\\像素爆炸_耳聆网_[声音ID：19717].wav");
        //把新创建的放进集合shots
        bullets.add(bullet);
        //启动Shot线程
        Thread thread = new Thread(bullet);
        thread.start();
    }

}
