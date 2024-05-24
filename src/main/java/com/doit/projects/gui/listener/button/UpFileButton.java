package com.doit.projects.gui.listener.button;

import com.doit.projects.gui.utils.FrameUtils;
import com.mysql.cj.util.StringUtils;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 功能描述
 *
 * @author: yexiaomin
 * @date: 2024年05月04日 16:39
 */
@Data
public class UpFileButton implements ActionListener {
    private String filePath;
    private Frame frame;
    public UpFileButton(Frame frame) {
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        FileDialog dialog = new FileDialog(frame, "请选择一个文件", FileDialog.LOAD);
        dialog.setDirectory("D:\\");
        dialog.setVisible(true);

        filePath = dialog.getDirectory()+dialog.getFile();
        if (!filePath.contains("null")) {
            // 用户选择了文件，获取并打印文件路径
            frame.add(FrameUtils.createLabel(filePath, 300, 110, 500, 50, 14));

        } else {
            JOptionPane.showMessageDialog(null, "没有选择文件", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
