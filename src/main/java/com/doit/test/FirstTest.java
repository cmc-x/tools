package com.doit.test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FirstTest extends JFrame implements ActionListener, KeyListener {

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private Timer timer;
    private int delay = 10; // 延迟时间，用于控制游戏速度
    private int playerX = WINDOW_WIDTH / 2; // 玩家初始位置
    private int bulletY = WINDOW_HEIGHT; // 子弹初始位置

    public FirstTest() {
        setTitle("简单打飞机游戏");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addKeyListener(this); // 添加键盘监听
        setVisible(true);

        timer = new Timer(delay, this);
        timer.start(); // 启动定时器
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillRect(playerX, WINDOW_HEIGHT - 50, 50, 10); // 玩家飞机
        g.setColor(Color.RED);
        g.fillRect(bulletY, 50, 10, 10); // 子弹
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bulletY -= 10; // 子弹向上移动
        repaint(); // 重绘界面

        // 简单的子弹超出边界消失逻辑
        if (bulletY < 0) {
            bulletY = WINDOW_HEIGHT;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && playerX > 0) playerX -= 10; // 左移
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && playerX < WINDOW_WIDTH - 50) playerX += 10; // 右移
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bulletY = WINDOW_HEIGHT - 50; // 发射子弹
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FirstTest());
    }
}



