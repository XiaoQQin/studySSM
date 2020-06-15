package com.hwm.dao;

import com.hwm.pojo.Student;

import java.util.List;

public interface StudentMapper {

    //查询所有的学生信息以及对应的老师信息


    public List<Student> getStudents();


    public List<Student> getStudents2();


}
