package com.steamedegg.controller;

import com.steamedegg.databaseservice.BlogDao;
import com.steamedegg.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Juntao Peng
 */
@Controller
public class BlogController {

    BlogDao blogDao;

    @Autowired
    public BlogController(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    @RequestMapping(value = "/blogs", method = RequestMethod.GET)
    public ModelAndView blogs(@RequestParam("page") int page) {
        ModelAndView mv = new ModelAndView("blogs");
        List<Blog> blogList = this.blogDao.queryAllBlogs((page - 1) * 20, 20);
        mv.addObject("blogList", blogList);
        return mv;
    }

    @RequestMapping(value = "/blogDetail", method = RequestMethod.GET)
    public ModelAndView blogDetail(@RequestParam("id") int id) {
        ModelAndView mv = new ModelAndView("blog_detail");
        Blog blog = this.blogDao.queryBlogById(id);
        mv.addObject("blog", blog);
        return mv;
    }
}
