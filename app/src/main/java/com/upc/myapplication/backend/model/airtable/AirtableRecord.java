package com.upc.myapplication.backend.model.airtable;

public class AirtableRecord<T> {
    private String id;
    private T fields;

    public AirtableRecord(T fields) {
        this.fields = fields;
    }

    public T getFields() {
        return fields;
    }

    public void setFields(T fields) {
        this.fields = fields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
