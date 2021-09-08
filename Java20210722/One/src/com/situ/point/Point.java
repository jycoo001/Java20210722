package com.situ.point;

public class Point {
    private int x;
    private int y;

    public Point() {
        System.out.println("Point.Point");
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void movePoint (int dx,int dy) {
        this.x = this.x + dx;
        this.y = this.y + dy;
        System.out.println("x坐标：" + this.x);
        System.out.println("y坐标：" + this.y);

    }
}
