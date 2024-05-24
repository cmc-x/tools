package com.doit.projects.gui;

import com.doit.projects.gui.listener.MouseAdapterImp;
import com.doit.projects.gui.listener.WindowAdapterImp;
import com.doit.projects.gui.listener.button.DownFileButton;
import com.doit.projects.gui.listener.button.ExecButton;
import com.doit.projects.gui.listener.button.UpFileButton;
import com.doit.projects.gui.utils.FrameUtils;

import java.awt.*;



/**
 * 这是一个工具类
 *
 * @author: yexiaomin
 * @date: 2024年05月04日 15:53
 */
public class ApplicationServer {

    public static void main(String[] args) {
        // 定义一个窗口
        Frame frame = new Frame();
        frame.setLayout(null);
        Dimension d = new Dimension(800,600);
        frame.setSize(d);
        //显示窗口
        frame.setVisible(true);
        // 是否可以改变窗口大小
        frame.setResizable(false);
        // 设置窗口标题
        frame.setTitle("测试窗口");
        //设置窗口位置
        frame.setLocation(FrameUtils.getPoint(frame));
        // 设置窗口监听事件 - 窗口生命周期事件(关闭窗口, 打开窗口等)
        frame.addWindowListener(new WindowAdapterImp(frame));
        // 设置鼠标监听事件
        frame.addMouseListener(new MouseAdapterImp(frame));

        //添加模组选择器
        CheckboxGroup group = new CheckboxGroup();   //创建勾选框组

        Checkbox c1 = new Checkbox("ExcelToJson");
        c1.setBounds(20, 50, 200, 30);
        c1.setFont(FrameUtils.getFont("宋体", Font.BOLD, 18));
        frame.add(c1);

        Checkbox c2 = new Checkbox("未知");
        c2.setBounds(20, 90, 200, 30);
        c2.setFont(FrameUtils.getFont("宋体", Font.BOLD, 18));
        frame.add(c2);

        c1.setCheckboxGroup(group);    //多个勾选框都可以添加到勾选框组中
        c2.setCheckboxGroup(group);    //多个勾选框都可以添加到勾选框组中


        // 添加标签 - 源文件路径
        frame.add(FrameUtils.createLabel("源文件路径:", 300, 50, 100, 50, 24));
        // 添加按钮 - 选择源文件
        Button upFileButton = FrameUtils.createButton("选择文件", 450, 50, 100, 50);
        UpFileButton upFileButton1Action = new UpFileButton(frame);
        upFileButton.addActionListener(upFileButton1Action);
        frame.add(upFileButton);

        // 设置目标路径
        frame.add(FrameUtils.createLabel("保存结果:", 300, 170, 100, 50, 24));
        // 添加按钮 - 选择目标文件路径
        Button downFileButton = FrameUtils.createButton("选择", 450, 170, 100, 50);
        DownFileButton downFileButton1Action = new DownFileButton(frame);
        downFileButton.addActionListener(downFileButton1Action);
        frame.add(downFileButton);

        // 添加目标文件名称文本
//        frame.add(FrameUtils.createLabel("目标文件名称:", 300, 290, 100, 50, 24));
//        TextField field = new TextField();   //TextField是文本框
//        field.setBounds(450, 290, 100, 50);
//        frame.add(field);

        //添加确定按钮
        Button execButton = FrameUtils.createButton("确定", 450, 350, 100, 50);
        ExecButton execButton1Action = new ExecButton(upFileButton1Action,downFileButton1Action, group, frame);
        execButton.addActionListener(execButton1Action);
        frame.add(execButton);

    }


}
