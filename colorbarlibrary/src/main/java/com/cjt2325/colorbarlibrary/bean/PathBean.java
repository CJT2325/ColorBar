package com.cjt2325.colorbarlibrary.bean;

import android.graphics.Path;

/**
 * =====================================
 * 作    者: 陈嘉桐
 * 版    本：
 * 创建日期：2017/5/31
 * 描    述：
 * =====================================
 */
public class PathBean {
    private int color;
    private Point startPoint;
    private Point endPoint;
    private Path path;

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
