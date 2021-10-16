package com.jyc.mapper;

import com.jyc.entity.Banji;
import com.jyc.vo.BanjiTongJi;
import com.jyc.vo.Page;

import java.util.ArrayList;

public interface BanjiMapper {
    public ArrayList<Banji> selectAll();
    public Banji selectById(Integer id);
    public void update(Banji banji);
    public void insert(Banji banji);
    public void deleteById(Integer id);
    public ArrayList<Banji> selectAllOne(Page page);
    public int selectTotalCount();
    public ArrayList<BanjiTongJi> selectBanjiTongji();
}
