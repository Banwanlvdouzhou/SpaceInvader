package SpaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;
@SuppressWarnings({"all"})//抑制，美观，嗯~ o(*￣▽￣*)o
public class Invaders implements Runnable{
    public int x;//敌人横坐标
    public int y;//敌人纵坐标
    public int xSpeed ;//待定。。。
    boolean islive = true;
    private int type = 0;
    Vector<Bullet> bullets =  new Vector<>();//加进去子弹
    private Vector<? extends Invaders> invaders = new Vector<>();//从画板拿来敌人
    private double hardLevel = 0.003;//算是简单好了
    private Timer timer;

    public double getHardLevel() {
        return hardLevel;
    }

    public void setHardLevel(double hardLevel) {
        this.hardLevel = hardLevel;
    }

    public Invaders(int x, int y, int xSpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        timer = new Timer(1000, e -> {
            if (type == 0) {
                type = 1;
            } else {
                type = 0;
            }
        });
        timer.start();
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

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void setInvaders(Vector<? extends Invaders> invaders) {
        this.invaders = invaders;
    }

    public void move() {
        x += xSpeed;
//        if (x < -50 || x >= 850) {
//            xSpeed = -xSpeed;
//            y += 50;
//        }
    }



    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public boolean isIslive() {
        return islive;
    }

    public void setIslive(boolean islive) {
        this.islive = islive;
    }
    public boolean shouldFireBullet(){//随机。。。。
        if (Math.random() < hardLevel) {
            return true;
        }
        return false;
    }
    public void drawinvaders(int x, int y, Graphics g) throws InterruptedException {
        switch (type) {
            case 0:
                g.setColor(Color.red);
                //5,,,5
                g.fill3DRect(x + 10, y, 5, 5, false);
                g.fill3DRect(x + 15, y + 5, 5, 5, false);
                g.fill3DRect(x + 10, y + 30, 5, 5, false);
                g.fill3DRect(x + 45, y, 5, 5, false);
                g.fill3DRect(x + 40, y + 5, 5, 5, false);
                g.fill3DRect(x + 45, y + 30, 5, 5, false);

                //5,,,10和10，，，5
                g.fill3DRect(x, y + 20, 5, 10, false);
                g.fill3DRect(x + 5, y + 15, 5, 10, false);
                g.fill3DRect(x + 50, y + 15, 5, 10, false);
                g.fill3DRect(x + 55, y + 20, 5, 10, false);
                g.fill3DRect(x + 15, y + 35, 13, 5, false);
                g.fill3DRect(x + 32, y + 35, 13, 5, false);

                //脸
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.setColor(Color.orange);
                g.fill3DRect(x + 17, y + 13, 6, 6, false);
                g.fill3DRect(x + 35, y + 13, 6, 6, false);
                g.setColor(Color.red);
                break;


            case 1:
                g.setColor(Color.red);
                //5,,,5
                g.fill3DRect(x + 10, y, 5, 5, false);
                g.fill3DRect(x + 15, y + 5, 5, 5, false);
                g.fill3DRect(x + 10, y + 30, 5, 5, false);
                g.fill3DRect(x + 45, y, 5, 5, false);
                g.fill3DRect(x + 40, y + 5, 5, 5, false);
                g.fill3DRect(x + 45, y + 30, 5, 5, false);

                //5,,,10和10，，，5
                g.fill3DRect(x, y + 10, 5, 10, false);
                g.fill3DRect(x + 5, y + 15, 5, 10, false);
                g.fill3DRect(x + 50, y + 15, 5, 10, false);
                g.fill3DRect(x + 55, y + 10, 5, 10, false);
                g.fill3DRect(x+5 , y + 35, 5, 5, false);
                g.fill3DRect(x + 50, y + 35, 5, 5, false);

                //脸
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.setColor(Color.orange);
                g.fill3DRect(x + 17, y + 13, 6, 6, false);
                g.fill3DRect(x + 35, y + 13, 6, 6, false);
                g.setColor(Color.red);
                break;

        }
    }
    @Override
    public synchronized void run() {
        while (true){
            if (islive && bullets.size() < 1 && shouldFireBullet()) {
                Bullet bullet = null;
                //下
                bullet = new Bullet(getX()+30,getY()+30,5,1);
                bullets.add(bullet);
                new Thread(bullet).start();
            }
            move();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (islive == false ) {//死了
                break;
            }
        }
    }
}