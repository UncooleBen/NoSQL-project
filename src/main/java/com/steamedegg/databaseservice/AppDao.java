package com.steamedegg.databaseservice;

import com.steamedegg.model.App;

/**
 * @author Juntao Peng
 */
public interface AppDao {
    public App queryAppByAppId(int appId);
}
