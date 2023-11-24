package com.example.tliasproject.controller;

import com.example.tliasproject.anno.Log;
import com.example.tliasproject.pojo.Dept;
import com.example.tliasproject.pojo.Result;
import com.example.tliasproject.service.DeptService;
import com.example.tliasproject.service.impl.DeptServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list() {
        log.info("查询所有部门信息");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        log.info("删除路径传递的id值的数据");
        deptService.deleteById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("增加新部门:{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable String id){
        log.info("根据ID查询");
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    public Result put(@RequestBody Dept dept){
        log.info("修改部门名称信息,以及更新时间");
        deptService.update(dept);
        return Result.success();
    }


}
