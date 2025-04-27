package com.upc.myapplication.backend.dao;

import com.google.gson.Gson;
import com.upc.myapplication.BuildConfig;
import com.upc.myapplication.backend.dao.datasource.AirtableClient;
import com.upc.myapplication.backend.model.Book;
import com.upc.myapplication.backend.model.airtable.AirtableRecord;
import com.upc.myapplication.backend.model.airtable.BookFields;
import com.upc.myapplication.backend.model.airtable.AirtableResponse;
import java.io.IOException;
import java.net.URLEncoder;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

public class BookDao {
    private static String entityId = BuildConfig.AIRTABLE_BOOK_TABLE;

    public static AirtableRecord<BookFields>[] getAllBooks(){
        return searchBooks("");
    }

    public static AirtableRecord<BookFields>[] getAvailableBooks(){
        String filter = "AND({Available} > 0)";
        return searchBooks(filter);
    }

    public static AirtableRecord<BookFields> getByRecordId(@NotNull String recordId) {
        try {
            AirtableClient airtableClient = new AirtableClient(entityId);
            String result = airtableClient.getById(recordId);
            TypeToken<AirtableRecord<BookFields>> typeToken = new TypeToken<>(){};
            return new Gson().fromJson(result, typeToken.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AirtableRecord<BookFields>[] getBooksWithUsers(){
        String filter = "AND( {UserBook} != '' )";
        return searchBooks(filter);
    }

    private static AirtableRecord<BookFields>[] searchBooks(String filter){
        try {
            String encodedFilter = URLEncoder.encode(filter, "UTF-8");

            AirtableClient airtableClient = new AirtableClient(entityId);
            String results = airtableClient.searchBy(encodedFilter, null, null);

            TypeToken<AirtableResponse<AirtableRecord<BookFields>>> typeToken = new TypeToken<>(){};
            AirtableResponse<AirtableRecord<BookFields>> response = new Gson().fromJson(results, typeToken.getType());
            return response.getRecords();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
