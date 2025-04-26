package com.upc.myapplication.backend.model.airtable;

public class AirtableResponse<T> {
    private T[] records;

    private String offset;

    public T[] getRecords() {
        return records;
    }

    public String getOffset() {
        return offset;
    }
}