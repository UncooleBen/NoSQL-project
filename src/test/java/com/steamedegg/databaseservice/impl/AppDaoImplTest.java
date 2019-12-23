package com.steamedegg.databaseservice.impl;

import com.steamedegg.databaseservice.AppDao;
import com.steamedegg.model.App;
import org.junit.jupiter.api.Test;

import java.util.List;

class AppDaoImplTest {

    AppDao appDao = new AppDaoImpl();

    @Test
    void test() {
        List<App> list = appDao.queryAllApp(0, 5);
        for (App app : list) {
            System.out.println(app.getDetailedDescription());
        }
    }

}