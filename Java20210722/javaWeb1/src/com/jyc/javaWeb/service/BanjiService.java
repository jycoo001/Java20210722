package com.jyc.javaWeb.service;

import com.jyc.javaWeb.entity.Banji;
import com.jyc.javaWeb.util.PageInfo;
import com.jyc.javaWeb.vo.BanjiTongJi;

import java.util.List;

public interface BanjiService {
    public Banji selectOne(Integer id);
    public void update(Banji banji);
    public int insert(Banji banji, int pageSize, int totalCount);
    public void deleteById(Integer id);
    public PageInfo selectAll(int pageNumber, int pageSize, int totalCount);
    public int getCount();
    public List<BanjiTongJi> selectbanjitongji();
    public List<Banji> selectAllNoPage();
}
