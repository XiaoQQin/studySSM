package com.hwm.dao;

import com.hwm.pojo.Teacher2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper2 {


    Teacher2 getTeachers(@Param("tid") int id);
    Teacher2 getTeachers2(@Param("tid") int id);
}
