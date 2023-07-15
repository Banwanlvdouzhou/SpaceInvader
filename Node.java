package SpaceInvaders;
@SuppressWarnings({"all"})
public class Node {
    private int x;
    private int y;

    private int xSpeed;
    private int bulletNum;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, int xSpeed,int bulletNum) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.bulletNum = bulletNum;
    }

    public Node(int x, int y, int xSpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
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
}
