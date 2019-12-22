package com.steamedegg.model;

import java.util.List;

/**
 * @author Juntao Peng, Shangzhen Li
 */
public class App {
  private String type;
  private String name;
  private int steamAppId;
  private int requiredAge;
  private boolean isFree;
  private String detailedDescription;
  private String supportedLanguages;
  private List<String> developers;
  private List<String> publishers;
  private List<String> platforms;
  private int score;
  private List<String> categories;
  private List<String> genres;
  private int recommendations;
  private long releaseDate;
  private String backgroundURL;
  private String headerURL;

  public String getHeaderURL() {
    return headerURL;
  }

  public void setHeaderURL(String headerURL) {
    this.headerURL = headerURL;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSteamAppId() {
    return steamAppId;
  }

  public void setSteamAppId(int steamAppId) {
    this.steamAppId = steamAppId;
  }

  public int getRequiredAge() {
    return requiredAge;
  }

  public void setRequiredAge(int requiredAge) {
    this.requiredAge = requiredAge;
  }

  public boolean isFree() {
    return isFree;
  }

  public void setFree(boolean free) {
    isFree = free;
  }

  public String getDetailedDescription() {
    return detailedDescription;
  }

  public void setDetailedDescription(String detailedDescription) {
    this.detailedDescription = detailedDescription;
  }

  public String getSupportedLanguages() {
    return supportedLanguages;
  }

  public void setSupportedLanguages(String supportedLanguages) {
    this.supportedLanguages = supportedLanguages;
  }

  public List<String> getDevelopers() {
    return developers;
  }

  public void setDevelopers(List<String> developers) {
    this.developers = developers;
  }

  public List<String> getPublishers() {
    return publishers;
  }

  public void setPublishers(List<String> publishers) {
    this.publishers = publishers;
  }

  public List<String> getPlatforms() {
    return platforms;
  }

  public void setPlatforms(List<String> platforms) {
    this.platforms = platforms;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public List<String> getGenres() {
    return genres;
  }

  public void setGenres(List<String> genres) {
    this.genres = genres;
  }

  public int getRecommendations() {
    return recommendations;
  }

  public void setRecommendations(int recommendations) {
    this.recommendations = recommendations;
  }

  public long getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(long releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getBackgroundURL() {
    return backgroundURL;
  }

  public void setBackgroundURL(String backgroundURL) {
    this.backgroundURL = backgroundURL;
  }
}
