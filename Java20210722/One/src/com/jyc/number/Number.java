package com.jyc.number;

public class Number {
    private int num1;
    private int num2;

    public Number() {
        System.out.println("Number.Number");
    }

    public Number(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public int add () {
        return this.num1+this.num2;
    }

    public int sub () {
        return this.num1-this.num2;
    }

    public int multi () {
        return this.num1*this.num2;
    }

    public int div () {
        return this.num1 / this.num2;
    }
}
