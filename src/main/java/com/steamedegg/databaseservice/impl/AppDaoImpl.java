package com.steamedegg.databaseservice.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import com.steamedegg.databaseservice.AppDao;
import com.steamedegg.databaseservice.DatabaseService;
import com.steamedegg.model.App;
import com.steamedegg.model.Price;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

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
    private List<SimpleDateFormat> sdfs = Arrays.asList(
            new SimpleDateFormat("yyyy年MM月dd日"),
            new SimpleDateFormat("yyyy年MM月"),
            new SimpleDateFormat("yyyy年"));
    private SimpleDateFormat priceSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private App convertDocumentToApp(Document document) {
        String type = (String) document.get("type");
        String name = (String) document.get("name");
        int steamAppId = (int) document.get("steam_appid");
        int requiredAge = 0;
        if (document.get("required_age") instanceof String) {
            requiredAge = Integer.parseInt((String) document.get("required_age"));
        }
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
        if (categoriesList != null) {
            for (Document categoryDocument : categoriesList) {
                String category = (String) categoryDocument.get("description");
                categories.add(category);
            }
        }
        List<Price> prices = new ArrayList<>();
        List<Document> pricesList = (ArrayList<Document>) document.get("prices");
        if (pricesList != null) {
            for (Document priceDocument : pricesList) {
                Price price = new Price();
                String dateString = (String) priceDocument.get("date");
                String priceString = "";
                if (priceDocument.get("price") instanceof String) {
                    priceString = (String) priceDocument.get("price");
                } else {
                    priceString = String.valueOf((int) priceDocument.get("price"));
                }
                long date = 0;
                try {
                    date = this.priceSdf.parse(dateString).getTime();
                } catch (ParseException pe) {

                }
                price.setDate(date);
                price.setPrice(priceString);
                prices.add(price);
            }
        }
        String latestPrice = "";
        if (prices.size() > 0) {
            Price latest = prices.get(prices.size() - 1);
            latestPrice = latest.getPrice();
        }
        List<String> genres = new ArrayList<>();
        List<Document> genresList = (ArrayList<Document>) document.get("genres");
        if (genresList != null) {
            for (Document genreDocument : genresList) {
                String genre = (String) genreDocument.get("description");
                genres.add(genre);
            }
        }
        List<String> screenshots = new ArrayList<>();
        List<Document> screenshotsList = (ArrayList<Document>) document.get("screenshots");
        if (screenshotsList != null) {
            for (Document screenshotDocument : screenshotsList) {
                String screenshot = (String) screenshotDocument.get("path_full");
                screenshots.add(screenshot);
            }
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
            if (!"".equals(releaseString)) {
                for (SimpleDateFormat sdf : this.sdfs) {
                    try {
                        releaseDate = sdf.parse(releaseString).getTime();
                    } catch (ParseException pe) {

                    }
                }
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
        app.setScreenshots(screenshots);
        app.setPrices(prices);
        app.setLatestPrice(latestPrice);
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
                .find().sort(new BasicDBObject("recommendations.total", -1))
                .skip(skip).limit(limit).iterator();
        List<App> apps = new ArrayList<>();
        for (MongoCursor it = documents; it.hasNext(); ) {
            Object document = it.next();
            apps.add(convertDocumentToApp((Document) document));
        }
        return apps;
    }

    @Override
    public int queryDocumentNumber() {
        return (int) collection.countDocuments();
    }

}
