package com.contractoback.persistence.mongo;

import com.contractoback.entity.DictionaryTerm;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDictionaryDao {
    public static final String CONTRACTO = "contracto";
    public static final String DICTIONARY_TERM = "dictionaryTerm";
    MongoClient client;

    public MongoDictionaryDao() {
        client = new MongoClient();
    }

    public void saveTerm(DictionaryTerm term) {
        MongoDatabase database = client.getDatabase(CONTRACTO);
        MongoCollection<Document> collection = database.getCollection(DICTIONARY_TERM);

        Document doc = new Document("name", term.getName())
                .append("description", term.getDescription())
                .append("values", term.getValues());
        collection.insertOne(doc);
    }
}
