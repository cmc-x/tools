package com.doit.projects.gui.utils;

import java.awt.*;

/**
 * 功能描述
 *
 * @author: yexiaomin
 * @date: 2024年05月04日 15:54
 */
public class FrameUtils {
    /**
     * 根据屏幕分辨路动态设置窗口位置居中
     *
     * @author: yexiaomin
     * @date: 2024/5/4 15:38
     */
    public static Point getPoint(Frame frame) {
        //获取到屏幕尺寸
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Point point = new Point();
        point.y = (screenSize.height / 2 )-(frame.getHeight() / 2);
        point.x = (screenSize.width / 2 )-(frame.getWidth() / 2);
        return point;
    }

    /**
    * 创建标签
    *
    * @author: yexiaomin
    * @date: 2024/5/4 16:13
    */
    public static Label createLabel(String labesName, int locationX, int locationY, int sizeWidth, int sizeHeight, int fontSize){
        Label label = new Label();

        label.setFont(getFont("宋体", Font.BOLD, fontSize));
        label.setText(labesName);
        label.setLocation(locationX, locationY);
        label.setSize(sizeWidth, sizeHeight);
        return label;
    }

    /**
     * 创建标签
     *
     * @author: yexiaomin
     * @date: 2024/5/4 16:13
     */
    public static Button createButton(String labesName, int locationX, int locationY, int sizeWidth, int sizeHeight){
        Button button = new Button();

        button.setFont(getFont("宋体", Font.BOLD, 24));
        button.setLabel(labesName);
        button.setLocation(locationX, locationY);
        button.setSize(sizeWidth, sizeHeight);
        return button;
    }

    /**
     * 创建标签
     *
     * @author: yexiaomin
     * @date: 2024/5/4 16:13
     */
    public static Font getFont(String name, int style, int size){

        Font font = new Font("宋体", style, size);

        return font;
    }

}
