package com.rougamo.mybatis.mapper;

import com.rougamo.mybatis.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select * from Student where teacherId=#{teacherId}")
    List<Student> getStuentsByTeacherId(@Param("teacherId") Integer teacherId);

    @Select("select * from Student s, teacher t where s.teacherid=t.id and teacherid=#{teacherid}")
    List<Student> getStuents(@Param("teacherid") Integer teacherId);


}
