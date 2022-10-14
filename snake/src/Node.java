import java.util.Random;

public class Node {
    private int x;
    private int y;

    public Node() {
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
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

    //   随机生成食物的方法
    public void random() {
        Random r = new Random();
//        生成横坐标
        this.x = r.nextInt(4,20);
        this.y = r.nextInt(4,20);

    }
}
