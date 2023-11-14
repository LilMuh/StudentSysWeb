package com.tomh.studentspringboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tomh.studentspringboot.entity.Essay;
import com.tomh.studentspringboot.entity.Params;
import com.tomh.studentspringboot.mapper.EssayDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EssayService {

    @Resource
    private EssayDao essayDao;

    public PageInfo<Essay> search(Params params) {
        // start page search
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // automatically set
        List<Essay> list = essayDao.search(params);
        return PageInfo.of(list);
    }

    public void add(Essay essay) {
        essayDao.insertSelective(essay);
    }

    public void update(Essay essay) {
        essayDao.updateByPrimaryKeySelective(essay);
    }

    public void delete(int id) {
        essayDao.deleteByPrimaryKey(id);
    }
}