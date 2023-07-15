package SpaceInvaders;
@SuppressWarnings({"all"})//抑制，美观，嗯~ o(*￣▽￣*)o
public class Bullet implements Runnable {
    private int x;//子弹的x坐标
    private int y;//子弹的y坐标
    private int speed = 10;//子弹速度
    private boolean islive = true;//子但是否存活
    private int direction = 0;//子弹方向

    public Bullet(int x, int y, int speed, int direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direction) {
                case 0://向上
                    y-=speed;
                    break;
                case 1://向下
                    y+=speed;
                    break;
                case 2://左下
                    x-=speed;
                    y+=speed;
                    break;
                case 3://右下
                    x+=speed;
                    y+=speed;
                    break;
                case 4://左下下
                    x-=speed;
                    y+=2*speed;
                    break;
                case 5://右下下
                    x+=speed;
                    y+=2*speed;
                    break;
            }
            //当子弹碰到边界时，就销毁
            //当子弹碰到敌人时，也应该结束线程
            if (!(x >= 0 && x <= 900 && y >= 50 && y <= 900 && islive)) {
                islive = false;
                break;
            }
        }
    }
}
