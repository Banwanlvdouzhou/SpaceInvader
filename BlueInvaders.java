package SpaceInvaders;

import javax.swing.*;
import java.awt.*;
@SuppressWarnings({"all"})//抑制，美观，嗯~ o(*￣▽￣*)o
public class BlueInvaders extends Invaders  {

    private int type = 0;
    private Timer timer;
    public BlueInvaders(int x, int y, int xSpeed) {
        super(x, y, xSpeed);
        timer = new Timer(1000, e -> {
            if (type == 0) {
                type = 1;
            } else {
                type = 0;
            }
        });
        timer.start();
    }

        @Override
        public synchronized void move() {
            super.move();
        }

    @Override
    public void drawinvaders(int x, int y, Graphics g) throws InterruptedException {
        switch (type) {
            case 0:
                g.setColor(Color.CYAN);
                int[] xpoints3 = {x+20, x+20 ,x + 10, x+10,x+5 ,x+5 ,x+55,x+55,x+50,x+50,x+40,x+40};
                int[] ypoints3 = {y   , y+5  ,y + 5 , y+10,y+10,y+20,y+20,y+10,y+10,y+ 5,y+5 ,y   };
                g.fillPolygon(xpoints3, ypoints3, 12);

                g.fill3DRect(x+15,y+20,10,5,false);
                g.fill3DRect(x+35,y+20,10,5,false);

                g.fill3DRect(x+10,y+25,10,5,false);
                g.fill3DRect(x+25,y+25,10,5,false);
                g.fill3DRect(x+40,y+25,10,5,false);

                g.fill3DRect(x,y+30,10,5,false);
                g.fill3DRect(x+50,y+30,10,5,false);

                g.setColor(Color.black);
                g.fill3DRect(x+15,y+12,10,5,false);
                g.fill3DRect(x+35,y+12,10,5,false);
                g.setColor(Color.magenta);
                g.fill3DRect(x,y+33,10,2,false);
                g.fill3DRect(x+10,y+28,10,2,false);
                g.fill3DRect(x+25,y+28,10,2,false);
                g.fill3DRect(x+40,y+28,10,2,false);
                g.fill3DRect(x+50,y+33,10,2,false);
                g.setColor(Color.CYAN);
                break;

            case 1:
                g.setColor(Color.CYAN);
                int[] xpoints4 = {x+20, x+20 ,x + 10, x+10,x+5 ,x+5 ,x+55,x+55,x+50,x+50,x+40,x+40};
                int[] ypoints4 = {y   , y+5  ,y + 5 , y+10,y+10,y+20,y+20,y+10,y+10,y+ 5,y+5 ,y   };
                g.fillPolygon(xpoints4, ypoints4, 12);

                g.fill3DRect(x+15,y+20,10,5,false);
                g.fill3DRect(x+35,y+20,10,5,false);

                g.fill3DRect(x+10,y+25,10,5,false);
                g.fill3DRect(x+25,y+25,10,5,false);
                g.fill3DRect(x+40,y+25,10,5,false);


                g.fill3DRect(x+5 ,y+30,10,5,false);
                g.fill3DRect(x+45,y+30,10,5,false);
                g.fill3DRect(x+15,y+35,10,5,false);
                g.fill3DRect(x+35,y+35,10,5,false);

                g.setColor(Color.black);
                g.fill3DRect(x+15,y+12,10,5,false);
                g.fill3DRect(x+35,y+12,10,5,false);
                g.setColor(Color.magenta);
                g.fill3DRect(x+15,y+28,5,2,false);
                g.fill3DRect(x+25,y+28,10,2,false);
                g.fill3DRect(x+40,y+28,5,2,false);
                g.fill3DRect(x+5,y+33,10,2,false);
                g.fill3DRect(x+45,y+33,10,2,false);
                g.fill3DRect(x+15,y+38,10,2,false);
                g.fill3DRect(x+35,y+38,10,2,false);
                g.setColor(Color.CYAN);
                break;
        }

    }
    @Override
    public boolean shouldFireBullet() {
        return super.shouldFireBullet();
    }

    @Override
    public synchronized void run() {
        while (true){
            if (islive && bullets.size() <4 && shouldFireBullet()) {
                Bullet bullet1 = null;
                Bullet bullet2 = null;
                Bullet bullet3 = null;
                //下
                bullet1 = new Bullet(getX()+30,getY()+30,5,1);
                bullet2 = new Bullet(getX()+30,getY()+30,5,2);
                bullet3 = new Bullet(getX()+30,getY()+30,5,3);
                bullets.add(bullet1);
                bullets.add(bullet2);
                bullets.add(bullet3);
                new Thread(bullet1).start();
                new Thread(bullet2).start();
                new Thread(bullet3).start();
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

