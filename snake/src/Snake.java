import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Node> body;//蛇的身体
    static Image snakeody = Toolkit.getDefaultToolkit().getImage("imgs/tiaop.png");//蛇体素材
    static Image snakapple = Toolkit.getDefaultToolkit().getImage("imgs/limeon.png");//食物素材
    static Image snakhead = Toolkit.getDefaultToolkit().getImage("imgs/goutou.png");//食物素材
    private Direction direction = Direction.LEFT;
    public boolean isLiving = true;


    public Snake() {
//        初始化蛇
        initSnake();
    }

    private void initSnake() {
//        创建蛇的躯体
        body = new LinkedList<>();
//        添加三个节点
        body.add(new Node(20, 10));
//        body.add(new Node(21, 10));
//        body.add(new Node(22, 10));
//        body.add(new Node(22, 10));
//        body.add(new Node(23, 10));
//        body.add(new Node(24, 10));
//        body.add(new Node(25, 10));
//        body.add(new Node(26, 10));
//        body.add(new Node(27, 10));
    }

    //获取蛇身
    public LinkedList<Node> getBody() {
        return body;
    }

    //蛇移动
    public void move() {
        if (isLiving) {
            Node head = body.getFirst();//获取蛇头
            switch (direction) {
                case UP:
                    body.addFirst(new Node(head.getX(), head.getY() - 1));
                    break;
                case DOWN:
                    body.addFirst(new Node(head.getX(), head.getY() + 1));
                    break;
                case LEFT:
                    body.addFirst(new Node(head.getX() - 1, head.getY()));
                    break;
                case RIGHT:
                    body.addFirst(new Node(head.getX() + 1, head.getY()));
                    break;
            }
            body.removeLast();
            //判断蛇是否撞墙
            head = body.getFirst();
            if(head.getX()<0)
                head.setX(20);
            else if (head.getX()>20)
                head.setX(0);
            if(head.getY()<2)
                head.setY(19);
            else if (head.getY()>19)
                head.setY(2);
            //判断蛇是否撞到自己的身体
            for (int i = 1; i < body.size(); i++) {
                Node node = body.get(i);
                if (head.getX() == node.getX() && head.getY() == node.getY()) {
                    isLiving = false;
                }
            }
        }
    }

    public void setBody(LinkedList<Node> body) {
        this.body = body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void eat(Node food) {
        Node head = body.getFirst();//获取蛇头
        switch (direction) {
            case UP:
                body.addFirst(new Node(head.getX(), head.getY() - 1));
                break;
            case DOWN:
                body.addFirst(new Node(head.getX(), head.getY() + 1));
                break;
            case LEFT:
                body.addFirst(new Node(head.getX() - 1, head.getY()));
                break;
            case RIGHT:
                body.addFirst(new Node(head.getX() + 1, head.getY()));
                break;
        }
    }
}
