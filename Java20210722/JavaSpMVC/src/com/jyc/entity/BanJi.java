package com.jyc.entity;

public class BanJi {
    private Integer id;
    private String name;

    public BanJi() {
    }

    public BanJi(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BanJi{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
