package com.hwm.dao;

import com.hwm.pojo.Student;
import com.hwm.pojo.Teacher;
import com.hwm.pojo.Teacher2;
import com.hwm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class Test {

    @org.junit.Test
    public void testteacher2(){

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        TeacherMapper2 mapper = sqlSession.getMapper(TeacherMapper2.class);

        Teacher2 teachers2 = mapper.getTeachers2(1);

        System.out.println(teachers2);
        sqlSession.close();
    }

    @org.junit.Test
    public void testStudent(){

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.getStudents2();

        for (Student student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }
}
