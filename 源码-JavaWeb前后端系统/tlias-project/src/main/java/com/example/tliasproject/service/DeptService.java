package com.example.tliasproject.service;

import com.example.tliasproject.pojo.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> list();

    void deleteById(String id);

    void add(Dept dept);

    Dept getById(String id);

    void update(Dept dept);
}
