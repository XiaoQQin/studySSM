package com.hwm.dao;

import com.hwm.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {


    //插入数据
    int addBlog(Blog blog);

    //查询博客
    List<Blog> queryBlog(Map map);

    //查询博客
    List<Blog> queryBlogChoose(Map map);

    List<Blog> queryBlogChoose2(Map map);

    //更新博客
    int updateBlog(Map map);


    //查询1,2,3的记录的博客

    List<Blog> queryBlogForEach(Map map);

}
