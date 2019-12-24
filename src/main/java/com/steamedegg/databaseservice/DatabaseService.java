package com.steamedegg.databaseservice;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Juntao Peng
 */
@Component
public class DatabaseService {

    protected MongoClient mongoClient;
    protected MongoDatabase database;
    protected MongoCollection apps;
    protected MongoCollection blogs;
    @Value("${spring.datasource.dbUrl}")
    String dbUrl;


    public DatabaseService() {
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        this.mongoClient = new MongoClient(connectionString);
        this.database = this.mongoClient.getDatabase("steamdb");
        this.apps = this.database.getCollection("apps");
        this.blogs = this.database.getCollection("blogs");
    }
}
