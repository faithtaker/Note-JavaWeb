package com.example.tliasproject.service.impl;

import com.example.tliasproject.mapper.EmpMapper;
import com.example.tliasproject.pojo.Emp;
import com.example.tliasproject.pojo.PageBean;
import com.example.tliasproject.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpMapper empMapper;

    //    @Override
//    public PageBean pagingQuery(Integer page, Integer pageSize) {
//        PageBean pageBean = new PageBean();
//        pageBean.setTotal(empMapper.count());
//        Integer start = (page - 1) * pageSize;
//        pageBean.setRows(empMapper.page(start,pageSize));
//        return pageBean;
//    }
    @Override
    public PageBean pagingQuery(Integer page, Integer pageSize, String name, Short gender,
                                LocalDate begin,
                                LocalDate end) {
        PageHelper.startPage(page, pageSize);
        List<Emp> list = empMapper.getAllData(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) list;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void deleteData(ArrayList<Integer> ids) {
        empMapper.deleteData(ids);
    }

    @Override
    public void addData(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addData(emp);
    }

    @Override
    public Emp getDataById(Integer id) {
        return empMapper.getDataById(id);
    }

    public void updateData(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateData(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.login(emp);
    }

    @Override
    public void deleteByDeptID(Integer deptID) {
        empMapper.deleteByDeptID(deptID);
    }


}
