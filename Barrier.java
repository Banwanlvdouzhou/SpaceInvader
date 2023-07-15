package SpaceInvaders;

import java.util.Vector;
@SuppressWarnings({"all"})//抑制，美观，嗯~ o(*￣▽￣*)o
public class Barrier {
    private int x;//障碍物的x坐标
    private int y;//障碍物的y坐标
    private int size=20;//障碍物大小
    boolean islive = true;
    Vector<Barrier> barriers = new Vector<>();
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
    public Barrier(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getSize() {
        return size;
    }

    public boolean isIslive() {
        return islive;
    }

    public void setIslive(boolean islive) {
        this.islive = islive;
    }

    public Vector<Barrier> getBarriers() {
        return barriers;
    }

    public void setBarriers(Vector<Barrier> barriers) {
        this.barriers = barriers;
    }
}
