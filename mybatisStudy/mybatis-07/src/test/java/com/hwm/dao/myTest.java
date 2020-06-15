package com.hwm.dao;

import com.hwm.pojo.Blog;
import com.hwm.utils.IDutils;
import com.hwm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.omg.CORBA.MARSHAL;

import java.util.*;

public class myTest {
    @Test
    public void testAddBlog(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog=new Blog();
        blog.setId(IDutils.getId());
        blog.setTitle("java");
        blog.setAuthor("hwm");
        blog.setCreateTime(new Date());
        blog.setViews(500);


        mapper.addBlog(blog);

        blog.setId(IDutils.getId());
        blog.setTitle("spring");
        mapper.addBlog(blog);



        blog.setId(IDutils.getId());
        blog.setTitle("spring boot");
        mapper.addBlog(blog);

        blog.setId(IDutils.getId());
        blog.setTitle("mybatis");
        mapper.addBlog(blog);


        sqlSession.close();
    }


    @Test
    public void testQUeryBlog(){

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map=new HashMap();
        map.put("author","hwm");
        List<Blog> blogs = mapper.queryBlog(map);

        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();

    }


    @Test
    public void testQUeryChoose(){

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map=new HashMap();
        map.put("author","hwm");
        List<Blog> blogs = mapper.queryBlogChoose(map);

        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();

    }


    @Test
    public void testQUeryChoose2(){

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map=new HashMap();
        map.put("title","spring");
        map.put("views",9999);
        List<Blog> blogs = mapper.queryBlogChoose2(map);

        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();

    }


    @Test
    public void testBlog(){

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map=new HashMap();
        map.put("title","spring更新");
        map.put("views",231414);
        map.put("id","b557bd5272e3446f9ad6c7c1e2d303d5");
        mapper.updateBlog(map);
        sqlSession.close();

    }


    @Test
    public void testqueryBlogForEach(){

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map=new HashMap();
        List<String> ids=new ArrayList<String>();
        ids.add("1");
        ids.add("2");
        map.put("ids",ids);
        List<Blog> blogs = mapper.queryBlogForEach(map);

        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();

    }
}
