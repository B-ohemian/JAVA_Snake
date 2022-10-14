import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends JFrame {
    private Snake snake;//成员变量
    private JPanel jPanel;//画板变量
    private Timer timer;//在规定的时间内调用蛇移动的方法
    private Node food;//食物
    private static int score = 0;//分数
    boolean isStart = false;//是否开始


    //    主窗口
    public MainFrame() {
        initFrame();//初始化窗口
        initGamePanel();//初始化面板
        initSnake();//初始化蛇
        initFood();//初始化食物
        initTimer();//初始化定时器
        setKeyListener();//设置键盘监听

    }

    private void initFood() {
        food = new Node();
        food.random();
    }

    private void setKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (snake.getDirection() != Direction.DOWN) {
                            snake.setDirection(Direction.UP);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (snake.getDirection() != Direction.UP) {
                            snake.setDirection(Direction.DOWN);
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (snake.getDirection() != Direction.RIGHT) {
                            snake.setDirection(Direction.LEFT);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (snake.getDirection() != Direction.LEFT) {
                            snake.setDirection(Direction.RIGHT);
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        isStart =!isStart;
                        System.out.println(isStart);
                        break;
                }
            }
        });
    }

    //初始化定时器，让蛇能够移动
    private void initTimer() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {//定时任务
            @Override
            public void run() {
                if (isStart == true) {
                    snake.move();//调用移动方法
                    Node head = snake.getBody().getFirst();
                    if (head.getX() == food.getX() && head.getY() == food.getY()) {
                        snake.eat(food);
                        score = score + 1;
                        food.random();
                    }
                    jPanel.repaint();//刷新画面
                }

            }
        };
        //每100ms调用一次定时任务
        timer.scheduleAtFixedRate(timerTask, 0, 100);
    }

    //初始化蛇
    private void initSnake() {
        snake = new Snake();

    }

    //居中方法
    public void setFrameCenter() {
        //得到屏幕大小
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        //得到x的位置
        int x = (dimension.width - this.getWidth()) / 2;
        //得到y的位置
        int y = (dimension.height - this.getHeight()) / 2;
        //设置窗口位置
        this.setLocation(x, y);
    }

    //    初始化窗口
    private void initFrame() {
        setTitle("贪吃蛇");
        setSize(610, 640);
        setFrameCenter();//屏幕居中
        setResizable(false);//不可更改窗口大小
        setBackground(Color.gray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体关闭按钮的作用
        setVisible(true);


    }

    //    初始化游戏棋盘
    private void initGamePanel() {

        jPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                g.clearRect(0, 0, 600, 600);//清除屏幕
                g.setColor(Color.DARK_GRAY);
                g.fillRect(0, 60, 600, 600);//填充黑色背景
                g.setColor(Color.ORANGE);
                g.fillRect(0, 0, 600, 60);//填充黑色背景
                g.setColor(Color.WHITE);
                g.setFont(new Font("微软雅黑", Font.BOLD, 40));
                g.drawString("分数：" + score, 225, 45);
//                开始
                if (isStart == false) {
                    g.drawString("按空格键开始！", 180, 300);
                }

                //绘制蛇头
                LinkedList<Node> body = snake.getBody();
                for (int i = 1; i < body.size(); i++) {
                    g.drawImage(Snake.snakeody, body.get(i).getX() * 30, body.get(i).getY() * 30, 30, 30, null);
                }
                g.drawImage(Snake.snakhead, body.getFirst().getX() * 30, body.getFirst().getY() * 30, 30, 30, null);
                //绘制食物
                g.drawImage(Snake.snakapple, food.getX() * 30, food.getY() * 30, 30, 30, null);
                //                结束
                if (snake.isLiving==false){
                    g.drawString("游戏结束！", 200, 300);
                    g.drawString("按空格重新开始", 150, 340);
                    initSnake();
                    isStart=false;
                }

            }
        };
//        把棋盘添加到窗体中
        add(jPanel);
    }

    //    主函数
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }
}
