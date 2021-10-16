package com.jyc.service.impl;

import com.jyc.entity.Banji;
import com.jyc.mapper.BanjiMapper;
import com.jyc.service.BanjiService;
import com.jyc.util.PageInfo;
import com.jyc.vo.BanjiTongJi;
import com.jyc.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BanjiServiceImpl implements BanjiService {

    @Autowired
    private BanjiMapper banjiMapper;

    @Override
    public ArrayList<Banji> selectAll() {
        return banjiMapper.selectAll();
    }

    @Override
    public Banji selectOne(Integer id) {
        return banjiMapper.selectById(id);
    }

    @Override
    public void update(Banji banji) {
        banjiMapper.update(banji);
    }

    @Override
    public int insert(Banji banji, int pageSize) {
        int totalCount = getCount();
        banjiMapper.insert(banji);
        return (int)Math.ceil((double)totalCount / pageSize);
    }

    @Override
    public void deleteById(Integer id) {
        banjiMapper.deleteById(id);
    }

    @Override
    public PageInfo selectAllOne(int pageNumber, int pageSize) {
        int totalCount = getCount();
        int offset = (pageNumber-1) * pageSize;
        int totalPage = (int)Math.ceil((double)totalCount / pageSize);
        Page page = new Page(offset, pageSize);
        ArrayList<Banji> list = banjiMapper.selectAllOne(page);
        PageInfo pageInfo = new PageInfo(list, pageNumber, totalPage, pageSize);
        return pageInfo;
    }

    @Override
    public int getCount() {
        return banjiMapper.selectTotalCount();
    }

    @Override
    public ArrayList<BanjiTongJi> selectBanjiTongji() {
        return banjiMapper.selectBanjiTongji();
    }
}
