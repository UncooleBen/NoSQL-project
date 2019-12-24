package com.steamedegg.databaseservice;

import com.steamedegg.model.Blog;

import java.util.List;

/**
 * @author Juntao Peng
 */
public interface BlogDao {
    Blog queryBlogById(int id);

    List<Blog> queryAllBlogs(int skip, int limit);
}
