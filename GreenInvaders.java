package SpaceInvaders;
import javax.swing.Timer;
import java.awt.*;
@SuppressWarnings({"all"})//抑制，美观，嗯~ o(*￣▽￣*)o
public class GreenInvaders extends Invaders{
    private int type = 0;
    private Timer timer;
    public GreenInvaders(int x, int y, int xSpeed) {
        super(x, y, xSpeed);
        timer = new Timer(500, e -> {
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
        x += xSpeed;
        if (x < 0 || x >= 800 - 50) {
            xSpeed = -xSpeed;
            y += 50;
        }
    }

    @Override
    public void drawinvaders(int x, int y, Graphics g) throws InterruptedException {
        switch (type) {
            case 0:
                g.setColor(Color.GREEN);
                //5,,,5
                g.fill3DRect(x + 20, y + 25, 5, 5, false);
                g.fill3DRect(x + 15, y + 30, 5, 5, false);
                g.fill3DRect(x + 35, y + 25, 5, 5, false);
                g.fill3DRect(x + 40, y + 30, 5, 5, false);

                //5,,,10和10，，，5
                g.fill3DRect(x + 20, y + 35, 8, 5, false);
                g.fill3DRect(x + 33, y + 35, 7, 5, false);

                int[] xpoints1 = {x+25, x+25 ,x + 20, x+20, x+15,x+15,x+10,x+10,x+50,x+50,x+45,x+45,x+40,x+40,x+35,x+35};
                int[] ypoints1 = {y   , y+5  ,y + 5 , y+10, y+10,y+15,y+15,y+25,y+25,y+15,y+15,y+10,y+10,y+5 ,y+5 ,y};
                g.fillPolygon(xpoints1, ypoints1, 16);
                g.setColor(Color.ORANGE);
                g.fill3DRect(x + 20, y + 28, 5, 2, false);
                g.fill3DRect(x + 15, y + 33, 5, 2, false);
                g.fill3DRect(x + 20, y + 38, 8, 2, false);
                g.fill3DRect(x + 35, y + 28, 5, 2, false);
                g.fill3DRect(x + 40, y + 33, 5, 2, false);
                g.fill3DRect(x + 33, y + 38, 7, 2, false);
                //脸
                g.setColor(Color.BLACK);
                g.fill3DRect(x + 20, y + 17, 5, 5, false);
                g.fill3DRect(x + 35,y + 17 , 5, 5,false);
                g.setColor(Color.GREEN);
                break;

            case 1:
                g.setColor(Color.GREEN);
                //5,,,5
                g.fill3DRect(x + 20, y + 25, 5, 5, false);
                g.fill3DRect(x + 35, y + 25, 5, 5, false);
                g.fill3DRect(x + 10, y + 35, 5, 5, false);
                g.fill3DRect(x + 20, y + 35, 5, 5, false);
                g.fill3DRect(x + 35, y + 35, 5, 5, false);
                g.fill3DRect(x + 45, y + 35, 5, 5, false);

                g.fill3DRect(x + 15, y + 30, 30, 5, false);

                int[] xpoints2 = {x+25, x+25 ,x + 20, x+20, x+15,x+15,x+10,x+10,x+50,x+50,x+45,x+45,x+40,x+40,x+35,x+35};
                int[] ypoints2 = {y   , y+5  ,y + 5 , y+10, y+10,y+15,y+15,y+25,y+25,y+15,y+15,y+10,y+10,y+5 ,y+5 ,y};
                g.fillPolygon(xpoints2, ypoints2, 16);

                g.setColor(Color.ORANGE);
                g.fill3DRect(x + 10, y + 38, 5, 2, false);
                g.fill3DRect(x + 15, y + 33, 5, 2, false);
                g.fill3DRect(x + 20, y + 38, 5, 2, false);
                g.fill3DRect(x + 25, y + 33, 10, 2, false);
                g.fill3DRect(x + 35, y + 38, 5, 2, false);
                g.fill3DRect(x + 40, y + 33, 5, 2, false);
                g.fill3DRect(x + 45, y + 38, 5, 2, false);
                //脸
                g.setColor(Color.BLACK);
                g.fill3DRect(x + 20, y + 17, 5, 5, false);
                g.fill3DRect(x + 35,y + 17 , 5, 5,false);
                g.setColor(Color.GREEN);
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
            if (islive && bullets.size() < 1 && shouldFireBullet()) {
                Bullet bullet1 = null;
                Bullet bullet2 = null;
                //下
                bullet1 = new Bullet(getX()+30,getY()+30,5,4);
                bullet2 = new Bullet(getX()+30,getY()+30,5,5);
                bullets.add(bullet1);
                bullets.add(bullet2);
                new Thread(bullet1).start();
                new Thread(bullet2).start();
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
