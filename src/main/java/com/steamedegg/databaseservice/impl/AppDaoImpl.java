package com.steamedegg.databaseservice.impl;

import com.steamedegg.databaseservice.AppDao;
import com.steamedegg.databaseservice.DatabaseService;
import com.steamedegg.model.App;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class AppDaoImpl extends DatabaseService implements AppDao {
    @Override
    public App queryAppByAppId(int appId) {
        Document document = (Document) collection.find(eq("steam_appid", appId)).first();
        System.out.println(document.toJson());
        return null;
    }
    public static void main(String[] args) {
        AppDaoImpl appDao = new AppDaoImpl();
        appDao.queryAppByAppId(485670);
    }
}
