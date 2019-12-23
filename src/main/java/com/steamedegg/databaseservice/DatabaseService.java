package com.steamedegg.databaseservice;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService {

    protected MongoClient mongoClient;
    protected MongoDatabase database;
    protected MongoCollection collection;
    @Value("${spring.datasource.dbUrl}")
    String dbUrl;


    public DatabaseService() {
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        this.mongoClient = new MongoClient(connectionString);
        this.database = this.mongoClient.getDatabase("steamdb");
        this.collection = this.database.getCollection("apps");
    }

    public MongoDatabase getDatabase() {
        return this.database;
    }

    public MongoCollection getCollection() {
        return this.collection;
    }
}
