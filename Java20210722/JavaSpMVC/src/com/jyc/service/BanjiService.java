package com.jyc.service;

import com.jyc.entity.Banji;
import com.jyc.util.PageInfo;
import com.jyc.vo.BanjiTongJi;

import java.util.ArrayList;

public interface BanjiService {
    public ArrayList<Banji> selectAll();
    public Banji selectOne(Integer id);
    public void update(Banji banji);
    public int insert(Banji banji, int pageSize);
    public void deleteById(Integer id);
    public PageInfo selectAllOne(int pageNumber, int pageSize);
    public int getCount();
    public ArrayList<BanjiTongJi> selectBanjiTongji();
}
