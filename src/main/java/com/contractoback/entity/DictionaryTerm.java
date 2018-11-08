package com.contractoback.entity;

import org.bson.types.ObjectId;

import java.util.List;

public class DictionaryTerm {
    private ObjectId id;
    private String name;
    private String description;
    private List<String> values;

    public DictionaryTerm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
