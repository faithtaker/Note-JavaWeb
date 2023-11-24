package com.example.tliasproject.service;

import com.example.tliasproject.pojo.Emp;
import com.example.tliasproject.pojo.PageBean;

import java.time.LocalDate;
import java.util.ArrayList;

public interface EmpService {
    PageBean pagingQuery(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void deleteData(ArrayList<Integer> ids);

    void addData(Emp emp);

    Emp getDataById(Integer id);

    void updateData(Emp emp);

    Emp login(Emp emp);

    void deleteByDeptID(Integer deptID);
}
