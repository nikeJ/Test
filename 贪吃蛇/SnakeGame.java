import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.UnresolvedPermission;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.omg.CORBA.INTERNAL;

public class SnakeGame {
	// 地图的宽(列数)
	public static final int WIDTH = 35;

	// 地图的高（行数）
	public static final int HEIGTH = 9;

	// 地图
	private char[][] background = new char[HEIGTH][WIDTH];

	// 使用集合记录蛇的坐标
	LinkedList<Point> snake = new LinkedList<Point>();

	// 食物
	Point food;

	// 创建食物
	public void createFood() {
		Random random = new Random();
		while (true) {
			int x = random.nextInt(WIDTH);
			int y = random.nextInt(HEIGTH);
			if (background[y][x] != '*' && background[y][x] != '$'
					&& background[y][x] != '#') {
				food = new Point(x,y);
				break;
			}
		}
	}

	//显示食物
	public void showFood() {
		background[food.y][food.x] = '@';
	}
	
	//使用4个常量表示方向
	public static final int UP_ = 1;
	public static final int DOWN_= -1;
	public static final int LEFT_ = 2;
	public static final int RIGHT_ = -2;
	
	int currentDirection = -2;
	
	//移动蛇的方法
	public void move(){
		Point head = snake.getFirst();
		switch (currentDirection) {
		case UP_:
			snake.addFirst(new Point(head.x,head.y-1));
			break;
			
		case DOWN_:
			snake.addFirst(new Point(head.x,head.y+1));
			break;
			
		case LEFT_:
			snake.addFirst(new Point(head.x-1,head.y));
			break;
			
		case RIGHT_:
			snake.addFirst(new Point(head.x+1,head.y));
			break;
		}
		snake.removeLast();
	}
	
	//改变当前方向
	public void changeDirection(int newDirection) {
		if (currentDirection+newDirection!=0) {
			this.currentDirection = newDirection;
		}
	}
	
	// 初始化蛇身
	public void initSnake() {
		int x = WIDTH / 2;
		int y = HEIGTH / 2;
		snake.addFirst(new Point(x - 1, y));
		snake.addFirst(new Point(x, y));
		snake.addFirst(new Point(x + 1, y));
	}

	// 显示蛇
	public void showSnake() {
		Point head = snake.getFirst();
		background[head.y][head.x] = '$';
		for (int i = 1; i < snake.size(); i++) {
			Point body = snake.get(i);
			background[body.y][body.x] = '#';
		}
	}

	// 初始化地图
	public void initbackground() {
		for (int rows = 0; rows < background.length; rows++) {
			for (int cols = 0; cols < background[rows].length; cols++) {
				if (rows == 0 || rows == background.length - 1 || cols == 0
						|| cols == background[rows].length - 1) {
					background[rows][cols] = '*';

				} else {
					background[rows][cols] = ' ';
				}
			}
		}
	}

	public void showbackground() {
		// 打印二维数组
		for (int rows = 0; rows < background.length; rows++) {
			for (int cols = 0; cols < background[rows].length; cols++) {
				System.out.print(background[rows][cols]);
			}

			System.out.println();// 换行
		}
	}

	
	//刷新游戏状态
	public void refresh() {
		initbackground();
		showSnake();
		showFood();
		showbackground();
	}
	
	
	
	public static void main(String[] args) {
		SnakeGame snake = new SnakeGame();
		snake.initbackground();
		snake.initSnake();
		snake.createFood();
		snake.showFood();
		snake.showSnake();
		snake.showbackground();
		
		
		JFrame frame = new JFrame("方向盘");
		frame.add(new JButton("↑"),BorderLayout.NORTH);
		frame.add(new JButton("↓"),BorderLayout.SOUTH);
		frame.add(new JButton("←"),BorderLayout.WEST);
		frame.add(new JButton("→"),BorderLayout.EAST);
		JButton button = new JButton("点击控制方向");
		frame.add(button);
		
		button.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
			}
		
		
		
		
		});
		
		
		
		
	}

}