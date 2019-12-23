package com.steamedegg.databaseservice;

import com.steamedegg.model.App;

import java.util.List;

/**
 * This interface declares methods used to interact with mongodb collections `apps`
 *
 * @author Juntao Peng
 * @author Shangzhen Li
 */
public interface AppDao {

    /**
     * Query an app with a given steam app id.
     *
     * @param appId The app id
     * @return The app with the given appId
     */
    App queryAppByAppId(int appId);

    /**
     * Query a list of apps that the value in categories matches the specified categories list.
     *
     * @param categories The List of categories that a game should have
     * @param skip       Skip for a number of documents
     * @param limit      Limit the query result documents
     * @return A list of apps that commensurate the given categories
     */
    List<App> queryAppByCategory(List<String> categories, int skip, int limit);

    /**
     * Query a list of apps that the value in genres matches the specified genres list.
     *
     * @param genres The List of genres that a game should have
     * @param skip   Skip for a number of documents
     * @param limit  Limit the query result documents
     * @return A list of apps that commensurate the given genres
     */
    List<App> queryAppByGenres(List<String> genres, int skip, int limit);

    /**
     * Query a list of apps from the very start of the mongodb collection.
     *
     * @param skip  Skip for a number of documents
     * @param limit Limit the query result documents
     * @returnA list of apps that commensurate the given limit and skip
     */
    List<App> queryAllApp(int skip, int limit);

    /**
     * Query the number of documents.
     *
     * @return The total number of documents
     */
    int queryDocumentNumber();

}
