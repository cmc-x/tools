package com.doit.projects.gui.listener.button;

import com.doit.projects.gui.exec.ExecInterface;
import com.doit.projects.gui.exec.execImp.ExcelToJson;
import com.doit.projects.gui.listener.ToolSeachModel;
import com.doit.projects.gui.listener.WindowAdapterImp;
import com.mysql.cj.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * 功能描述
 *
 * @author: yexiaomin
 * @date: 2024年05月04日 16:57
 */
public class ExecButton implements ActionListener {
    private UpFileButton sourceFile;
    private DownFileButton targetFile;
    private TextField field;
    private CheckboxGroup group;
    private ExecInterface exec;
    private Frame frame;
    public ExecButton(UpFileButton sourceFile, DownFileButton targetFile, CheckboxGroup group, Frame frame) {
        this.sourceFile = sourceFile;
        this.targetFile = targetFile;
        this.field = field;
        this.group = group;
        this.frame =  frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Checkbox selectedCheckbox = group.getSelectedCheckbox();
        if(selectedCheckbox == null){
            Dialog dialog = new Dialog(frame, "请先选择工具", true);
            //第一个参数是父窗口或是父对话框（没错，对话框也可以由对话框唤起）
            //最后一个参数是当对话框展示时，是否让父窗口（对话框）无法点击
            dialog.setSize(200, 80);
            dialog.add(new Label("请先选择左侧工具"));
            dialog.setLocationRelativeTo(null);
            dialog.addWindowListener(new ToolSeachModel(dialog));
            dialog.setVisible(true);

            return;
        }
        String label = selectedCheckbox.getLabel();
        switch (label) {
            case "ExcelToJson":
                exec = new ExcelToJson();
                break;
            default:
                System.out.println("请先选择工具");
                return;
        }

        String filePath = sourceFile.getFilePath();
        String dirPath = targetFile.getDirPath();
//        String dirFileName = field.getText();
        if(StringUtils.isNullOrEmpty(filePath)) {
            System.out.println("还未选择源文件, 请先选择源文件");
            return;
        }
        if(StringUtils.isNullOrEmpty(dirPath)) {
            System.out.println("还未选择目标文件夹, 请先选择目标文件夹");
            return;
        }
//        if(StringUtils.isNullOrEmpty(dirFileName)) {
//            System.out.println("还未填写目标文件名称, 请先填写目标文件名称");
//            return;
//        }

        System.out.println("准备执行工具:==* "+label+" *==");

        exec.exec(filePath, dirPath);

        System.out.println();
        JOptionPane.showMessageDialog(null, "任务:==* "+label+" *== 执行完成", "提示", JOptionPane.INFORMATION_MESSAGE);
    }
}
