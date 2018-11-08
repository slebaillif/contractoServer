package com.contractoback.persistence.mongo;

import com.contractoback.entity.DictionaryTerm;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDictionaryDao {
    public static final String CONTRACTO = "contracto";
    public static final String DICTIONARY_TERM = "dictionaryTerm";
    MongoClient client;

    public MongoDictionaryDao() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        client = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
    }

    public void saveTerm(DictionaryTerm term) {
        MongoDatabase database = client.getDatabase(CONTRACTO);
        MongoCollection<DictionaryTerm> collection = database.getCollection(DICTIONARY_TERM, DictionaryTerm.class);

        collection.insertOne(term);
    }

    public Collection<DictionaryTerm> getAllTerms() throws IOException {
        MongoDatabase database = client.getDatabase(CONTRACTO);
        MongoCollection<DictionaryTerm> collection = database.getCollection(DICTIONARY_TERM, DictionaryTerm.class);
        ArrayList result = new ArrayList();

        MongoCursor<DictionaryTerm> it = collection.find().iterator();
        while (it.hasNext()) {
            DictionaryTerm d = it.next();
            result.add(d);
        }
        return result;
    }

}
