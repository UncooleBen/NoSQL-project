package com.steamedegg.databaseservice.impl;

import com.steamedegg.databaseservice.AppDao;
import com.steamedegg.databaseservice.DatabaseService;
import com.steamedegg.model.App;
import com.steamedegg.model.Category;
import org.bson.Document;

import java.awt.*;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class AppDaoImpl extends DatabaseService implements AppDao {
    @Override
    public App queryAppByAppId(int appId) {
        Document document = (Document) collection.find(eq("steam_appid", appId)).first();
        System.out.println(document.toJson());
        return null;
    }

    @Override
    public List<App> queryAppByCategory(List<Category> categories, int skip, int limit) {
        return null;
    }

    @Override
    public List<App> queryAllApp(int skip, int limit) {
        return null;
    }


}
