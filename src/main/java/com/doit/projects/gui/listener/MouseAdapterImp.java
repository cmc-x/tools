package com.doit.projects.gui.listener;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 监听鼠标事件的实现类
 *
 * @author: yexiaomin
 * @date: 2024年05月04日 15:59
 */
public class MouseAdapterImp extends MouseAdapter {
    private Frame frame;
    public MouseAdapterImp(Frame frame) {
        this.frame = frame;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println(String.format("鼠标(%s)点击: x:%s y:%s", e.getButton(), e.getX(), e.getY()));

    }
}
