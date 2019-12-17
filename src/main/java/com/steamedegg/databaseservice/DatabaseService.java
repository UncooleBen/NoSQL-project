package com.steamedegg.databaseservice;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService {

    @Value("${spring.datasource.dbUrl}")
    String dbUrl;

    protected MongoClient mongoClient;
    protected MongoDatabase database;
    protected MongoCollection collection;



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
