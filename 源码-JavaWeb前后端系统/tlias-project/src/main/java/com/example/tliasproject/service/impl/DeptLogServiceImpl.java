package com.example.tliasproject.service.impl;


import com.example.tliasproject.mapper.DeptLogMapper;
import com.example.tliasproject.pojo.DeptLog;
import com.example.tliasproject.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl implements DeptLogService {

    @Autowired
    private DeptLogMapper deptLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW) //这个是开个新事物 无论成功与否都记录日志
    @Override
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
