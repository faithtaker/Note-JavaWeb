package com.example.tliasproject.controller;

import com.example.tliasproject.anno.Log;
import com.example.tliasproject.pojo.Emp;
import com.example.tliasproject.pojo.PageBean;
import com.example.tliasproject.pojo.Result;
import com.example.tliasproject.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    EmpService empService;


    /**
     * 1.分页查询
     * 2.带条件的分页查询
     */
    @GetMapping
    public Result pagingQuery(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              String name,
                              Short gender,
                              @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                              @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("参数:{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.pagingQuery(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result deleteData(@PathVariable ArrayList<Integer> ids) {
        log.info("删除数据id:{}", ids);
        empService.deleteData(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result addData(@RequestBody Emp emp) {
        log.info("新增员工:{}", emp);
        empService.addData(emp);
        return Result.success();
    }

    //    修改: 1.根据ID查询员工信息  2.修改找到的员工的信息
    @GetMapping("/{id}")
    public Result getDataById(@PathVariable Integer id) {
        log.info("需要查到的员工id:{}", id);
        Emp emp = empService.getDataById(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result updateData(@RequestBody Emp emp) {
        log.info("修改信息的员工id:{}", emp.getId());
        empService.updateData(emp);
        return Result.success();
    }


}
