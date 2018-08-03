package com.pork.mybatis.mapper;


import com.pork.mybatis.pojo.Teacher;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface TeacherMappber {

    @SelectProvider(type = TeacherProvider.class, method = "getTeachers")
    @Results({
            @Result(property = "id", column = "id"),
//            @Result(property = "name", column = "name"),
            @Result(property = "students", column = "id", many = @Many(select = "com.rougamo.mybatis.mapper.StudentMapper.getStuentsByTeacherId"))
    })
    List<Teacher> getTeachers();


    class TeacherProvider {
        public String getTeachers() {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("teacher");
                }
            }.toString();
        }
    }
}
