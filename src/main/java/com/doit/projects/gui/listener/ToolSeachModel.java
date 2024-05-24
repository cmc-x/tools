package com.doit.projects.gui.listener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 关闭弹窗
 *
 * @author: yexiaomin
 * @date: 2024年05月05日 15:25
 */
public class ToolSeachModel extends WindowAdapter {

    private Dialog dialog;
    public ToolSeachModel(Dialog dialog){
        this.dialog = dialog;
    }


    @Override
    public void windowClosing(WindowEvent e) {
        dialog.setVisible(false);
    }
}
