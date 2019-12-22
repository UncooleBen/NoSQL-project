package com.steamedegg.databaseservice.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.steamedegg.databaseservice.AppDao;
import com.steamedegg.databaseservice.DatabaseService;
import com.steamedegg.model.App;
import org.bson.Document;

import javax.print.Doc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.all;
import static com.mongodb.client.model.Filters.eq;

/**
 * @author Juntao Peng
 */
public class AppDaoImpl extends DatabaseService implements AppDao {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

    private App convertDocumentToApp(Document document) {
        String type = (String) document.get("type");
        String name = (String) document.get("name");
        int steamAppId = (int) document.get("steam_appid");
        int requiredAge = (int) document.get("required_age");
        boolean isFree = (boolean) document.get("is_free");
        String detailedDescription = (String) document.get("detailed_description");
        String supportedLanguages = (String) document.get("supported_languages");
        List<String> developers = (ArrayList<String>) document.get("developers");
        List<String> publishers = (ArrayList<String>) document.get("publishers");
        List<String> platforms = new ArrayList<>();
        Document platformsDocument = (Document) document.get("platforms");
        boolean windows = (boolean) platformsDocument.get("windows");
        boolean mac = (boolean) platformsDocument.get("mac");
        boolean linux = (boolean) platformsDocument.get("linux");
        if (windows) {
            platforms.add("Windows");
        }
        if (mac) {
            platforms.add("macOS");
        }
        if (linux) {
            platforms.add("Linux");
        }
        int score = 0;
        Document metacriticDocument = (Document) document.get("metacritic");
        if (metacriticDocument != null && metacriticDocument.get("score") != null) {
            score = (int) metacriticDocument.get("score");
        }
        List<String> categories = new ArrayList<>();
        List<Document> categoriesList = (ArrayList<Document>) document.get("categories");
        for (Document categoryDocument : categoriesList) {
            String category = (String) categoryDocument.get("description");
            categories.add(category);
        }
        List<String> genres = new ArrayList<>();
        List<Document> genresList = (ArrayList<Document>) document.get("genres");
        for (Document genreDocument : genresList) {
            String genre = (String) genreDocument.get("description");
            genres.add(genre);
        }
        int recommendations = 0;
        Document recommendationsDocument = (Document) document.get("recommendations");
        if (recommendationsDocument != null && recommendationsDocument.get("total") != null) {
            recommendations = (int) recommendationsDocument.get("total");
        }
        Document releaseDateDocument = (Document) document.get("release_date");
        boolean isComing = (boolean) releaseDateDocument.get("coming_soon");
        long releaseDate = 0;
        if (!isComing) {
            String releaseString = (String) releaseDateDocument.get("date");
            try {
                releaseDate = this.sdf.parse(releaseString).getTime();
            } catch (ParseException pe) {
                pe.printStackTrace(System.err);
            }
        }
        String backgroundURL = (String) document.get("background");
        String headerURL = (String) document.get("header_image");
        App app = new App();
        app.setType(type);
        app.setName(name);
        app.setSteamAppId(steamAppId);
        app.setRequiredAge(requiredAge);
        app.setFree(isFree);
        app.setDetailedDescription(detailedDescription);
        app.setSupportedLanguages(supportedLanguages);
        app.setDevelopers(developers);
        app.setPublishers(publishers);
        app.setPlatforms(platforms);
        app.setScore(score);
        app.setCategories(categories);
        app.setGenres(genres);
        app.setRecommendations(recommendations);
        app.setReleaseDate(releaseDate);
        app.setBackgroundURL(backgroundURL);
        app.setHeaderURL(headerURL);
        return app;
    }
    @Override
    public App queryAppByAppId(int appId) {
        Document document = (Document) collection.find(eq("steam_appid", appId)).first();
        return convertDocumentToApp(document);
    }

    @Override
    public List<App> queryAppByCategory(List<String> categories, int skip, int limit) {
        MongoCursor documents = collection
                .find(all("categories.description", categories))
                .skip(skip).limit(limit).iterator();
        List<App> apps = new ArrayList<>();
        for (MongoCursor it = documents; it.hasNext(); ) {
            Object document = it.next();
            apps.add(convertDocumentToApp((Document) document));
        }
        return apps;
    }

    @Override
    public List<App> queryAppByGenres(List<String> genres, int skip, int limit) {
        MongoCursor documents = collection
                .find(all("genres.description", genres))
                .skip(skip).limit(limit).iterator();
        List<App> apps = new ArrayList<>();
        for (MongoCursor it = documents; it.hasNext(); ) {
            Object document = it.next();
            apps.add(convertDocumentToApp((Document) document));
        }
        return apps;
    }
    
    @Override
    public List<App> queryAllApp(int skip, int limit) {
        MongoCursor documents = collection
                .find()
                .skip(skip).limit(limit).iterator();
        List<App> apps = new ArrayList<>();
        for (MongoCursor it = documents; it.hasNext(); ) {
            Object document = it.next();
            apps.add(convertDocumentToApp((Document) document));
        }
        return apps;
    }


}
