package com.doit.projects.gui.listener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 监听窗口事件的实现类
 *
 * @author: yexiaomin
 * @date: 2024年05月04日 15:45
 */
public class WindowAdapterImp extends WindowAdapter {
    private Frame frame;
    public WindowAdapterImp(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println(frame.getTitle()+"窗口已经打开");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        frame.dispose();

    }

    @Override
    public void windowClosed(WindowEvent e) {
        String title = frame.getTitle();
        System.out.println("窗口 #"+ title +"# 已经关闭");
        try {
            Thread.sleep(1000);
            System.exit(0);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
