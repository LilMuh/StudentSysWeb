package com.tomh.studentspringboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tomh.studentspringboot.entity.Admin;
import com.tomh.studentspringboot.entity.Params;
import com.tomh.studentspringboot.mapper.AdminDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {

    @Resource
    private AdminDao adminDao;

    public List<Admin> getAdmin() {
        return adminDao.selectAll();
    }

    public PageInfo<Admin> search(Params params) {
        // start page search
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        // automatically set
        List<Admin> list = adminDao.search(params);
        return PageInfo.of(list);
    }

    public void add(Admin admin) {
        adminDao.insertSelective(admin);
    }

    public void update(Admin admin) {
        adminDao.updateByPrimaryKeySelective(admin);
    }

    public void delete(int id) {
        adminDao.deleteByPrimaryKey(id);
    }
}
