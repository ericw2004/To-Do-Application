/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.todoapp;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 *
 * @author Sound Room
 */
public class Mongo {

    //your mongodb url here
    static String yourURL = "";
    
    public static final String DB_URL = yourURL;

    public static MongoClient getConnection() {
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(DB_URL))
                        //.applyToSslSettings(builder -> builder.enabled(true).invalidHostNameAllowed(true))
                        .build());
        return mongoClient;
    }

}
