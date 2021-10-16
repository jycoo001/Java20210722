package com.jyc.daili.sta;

public class Two implements One {

    private Three three;
    public Two(Three three) {
        this.three = three;
    }

    @Override
    public void show() {

        System.out.println("Two.show before");
        three.show();
        System.out.println("Two.show before");

    }
}
