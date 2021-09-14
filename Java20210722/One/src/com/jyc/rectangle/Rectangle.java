package com.jyc.rectangle;

public class Rectangle {
    private int wide;
    private int high;
    public Rectangle () {
        this.wide = 1;
        this.high = 1;
    }
    public Rectangle (int wideHigh) {
        this.high = wideHigh;
        this.wide = wideHigh;
    }
    public Rectangle (int wide,int high) {
        this.wide = wide;
        this.high = high;
    }

    public int getWide() {
        return wide;
    }

    public void setWide(int wide) {
        this.wide = wide;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "wide=" + wide +
                ", high=" + high +
                '}';
    }

    public int Girth () {
        return (this.wide+this.high)*2;
    }
    public int Area () {
        return this.wide*this.high;
    }

}
