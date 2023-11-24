package com.example.tliasproject.controller;

import com.example.tliasproject.pojo.Emp;
import com.example.tliasproject.pojo.Result;
import com.example.tliasproject.service.EmpService;
import com.example.tliasproject.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    EmpService empService;

    @PostMapping
    public Result login(@RequestBody Emp emp) {
        log.info("正在实现Login,username:{},password:{}", emp.getUsername(), emp.getPassword());
        Emp e = empService.login(emp);

        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        return Result.error("用户名或密码错误!");
    }


}
