package com.doit.test;

import javax.swing.*;
import java.io.File;

/**
 * 功能描述
 *
 * @author: yexiaomin
 * @date: 2024年04月30日 16:19
 */
public class SeachFile {
    public static void main(String[] args) {
            // 创建一个JFileChooser实例
            JFileChooser fileChooser = new JFileChooser();

            // 设置默认打开的目录（可选）
            // fileChooser.setCurrentDirectory(new File("C:\\Users\\YourPath"));

            // 设置文件选择模式（文件选择模式、目录选择模式等）
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            // 添加文件过滤器（例如，仅显示.txt文件）
            // fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "*.txt"));

            // 弹出文件选择对话框并等待用户操作
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                // 用户选择了文件，获取并打印文件路径
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("选定的文件路径: " + selectedFile.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(null, "没有选择文件", "提示", JOptionPane.INFORMATION_MESSAGE);
            }

    }
}
