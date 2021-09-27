package com.jyc.javaWeb.service.impl;

import com.jyc.javaWeb.dao.BanjiDao;
import com.jyc.javaWeb.dao.impl.BanjiDaoImpl;
import com.jyc.javaWeb.entity.Banji;
import com.jyc.javaWeb.service.BanjiService;
import com.jyc.javaWeb.util.PageInfo;
import com.jyc.javaWeb.vo.BanjiTongJi;

import java.util.ArrayList;
import java.util.List;

public class BanjiServiceImpl implements BanjiService {
    private BanjiDao banjiDao = new BanjiDaoImpl();
    @Override
    public Banji selectOne(Integer id) {
        return banjiDao.selectOne(id);
    }

    @Override
    public void update(Banji banji) {
        banjiDao.update(banji);
    }

    @Override
    public int insert(Banji banji, int pageSize, int totalCount) {
        banjiDao.insert(banji);
        return (int)Math.ceil((double)totalCount / pageSize);
    }

    @Override
    public void deleteById(Integer id) {
        banjiDao.deleteById(id);
    }

    @Override
    public PageInfo selectAll(int pageNumber, int pageSize, int totalCount) {
        int offset = (pageNumber-1) * pageSize;
        int totalPage = (int)Math.ceil((double)totalCount / pageSize);
        ArrayList<Banji> list = banjiDao.selectAll(offset, pageSize);
        PageInfo pageInfo = new PageInfo(list, pageNumber, totalPage, pageSize);
        return pageInfo;
    }

    @Override
    public int getCount() {
        return banjiDao.getCount();
    }

    @Override
    public List<BanjiTongJi> selectbanjitongji() {
        return banjiDao.selectbanjitongji();
    }

    @Override
    public List<Banji> selectAllNoPage() {
        return banjiDao.selectAllNoPage();
    }
}
