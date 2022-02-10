package com.dam2;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class Connection {
    private static MongoDatabase mgDb = null;

    private Connection() {

    }

    public static MongoDatabase getInstance()
    {
        if(mgDb == null){
            CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            MongoClient mongoClient = new MongoClient("localhost",
                    MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
            MongoDatabase mgDb = mongoClient.getDatabase("jsoro_ejerciciosMongo");

            return mgDb;
        }
        return mgDb;
    }
}
