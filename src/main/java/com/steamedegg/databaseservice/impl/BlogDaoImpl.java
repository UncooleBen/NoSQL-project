package com.steamedegg.databaseservice.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import com.steamedegg.databaseservice.BlogDao;
import com.steamedegg.databaseservice.DatabaseService;
import com.steamedegg.model.Blog;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * @author Juntao Peng
 */
public class BlogDaoImpl extends DatabaseService implements BlogDao {

    private Blog convertDocumentToBlog(Document document) {
        int id = (int) document.get("id");
        String title = (String) document.get("title");
        String author = (String) document.get("author");
        long date = (long) document.get("date");
        String subtitle = (String) document.get("subtitle");
        String content = (String) document.get("content");
        Blog blog = new Blog();
        blog.setDate(date);
        blog.setTitle(title);
        blog.setId(id);
        blog.setAuthor(author);
        blog.setSubtitle(subtitle);
        blog.setContent(content);
        return blog;
    }

    @Override
    public Blog queryBlogById(int id) {
        Document document = (Document) blogs.find(eq("id", id)).first();
        System.out.println(document);
        return convertDocumentToBlog(document);
    }

    @Override
    public List<Blog> queryAllBlogs(int skip, int limit) {
        MongoCursor documents = blogs
                .find().sort(new BasicDBObject("date", -1))
                .skip(skip).limit(limit).iterator();
        List<Blog> blogs = new ArrayList<>();
        for (MongoCursor it = documents; it.hasNext(); ) {
            Object document = it.next();
            blogs.add(convertDocumentToBlog((Document) document));
        }
        return blogs;
    }
}
