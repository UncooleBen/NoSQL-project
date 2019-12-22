package com.steamedegg.databaseservice;

import com.steamedegg.model.App;
import com.steamedegg.model.Category;
import java.util.List;

/** @author Juntao Peng */
public interface AppDao {
    App queryAppByAppId(int appId);
    List<App> queryAppByCategory(List<Category> categories, int skip, int limit);
    List<App> queryAllApp(int skip, int limit);
}
