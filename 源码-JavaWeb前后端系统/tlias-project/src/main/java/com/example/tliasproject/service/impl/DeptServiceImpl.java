package com.example.tliasproject.service.impl;

import com.example.tliasproject.mapper.DeptLogMapper;
import com.example.tliasproject.mapper.DeptMapper;
import com.example.tliasproject.mapper.EmpMapper;
import com.example.tliasproject.pojo.Dept;
import com.example.tliasproject.pojo.DeptLog;
import com.example.tliasproject.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogMapper deptLogMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class) //事务相关的注解 rollback属性设置的是不管何种异常都回滚
    @Override
    public void deleteById(String id) {
        try {
            deptMapper.deleteById(id);
            empMapper.deleteByDeptID(Integer.valueOf(id));
        } finally {
            DeptLog log = new DeptLog();
            log.setCreateTime(LocalDateTime.now());
            log.setDescription("解散部门id:" + id);
            deptLogMapper.insert(log);
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(String id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        //dept是接收了要改的数据的ID , 以及需要修改的地方
        //创建了一个newDept 装 根据 dept的id 找到的要改的数据 , 以及把要改的地方更新上去
//        Dept newDept = getById(String.valueOf(dept.getId()));
        Dept newDept = deptMapper.getById(String.valueOf(dept.getId()));
        newDept.setName(dept.getName());
        newDept.setUpdateTime(LocalDateTime.now());
        //把newDept传过去 , 数据库根据newDept进行修改数据
        deptMapper.update(newDept);
    }
}
