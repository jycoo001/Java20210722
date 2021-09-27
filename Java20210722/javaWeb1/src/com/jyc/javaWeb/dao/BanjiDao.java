package com.jyc.javaWeb.dao;

import com.jyc.javaWeb.entity.Banji;
import com.jyc.javaWeb.vo.BanjiTongJi;

import java.util.ArrayList;
import java.util.List;

public interface BanjiDao {
    public Banji selectOne(Integer id);
    public void update(Banji banji);
    public void insert(Banji banji);
    public void deleteById(Integer id);
    public ArrayList<Banji> selectAll(int offset, int pageSize);
    public int getCount();
    public List<BanjiTongJi> selectbanjitongji();
    public List<Banji> selectAllNoPage();
}
