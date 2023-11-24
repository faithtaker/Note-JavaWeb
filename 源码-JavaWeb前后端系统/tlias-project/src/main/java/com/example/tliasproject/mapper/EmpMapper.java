package com.example.tliasproject.mapper;

import com.example.tliasproject.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface EmpMapper {
//    @Select("select count(*) from emp")
//    public Long count();
//
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start, Integer pageSize);

    //    转到了xml文件进行动态sql
    public List<Emp> getAllData(String name, Short gender, LocalDate begin, LocalDate end);

    void deleteData(ArrayList<Integer> ids);

    void addData(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getDataById(Integer id);

    void updateData(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp login(Emp emp);

    @Delete("delete from emp where dept_id = #{deptID}")
    void deleteByDeptID(Integer deptID);
}
