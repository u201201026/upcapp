package com.upc.myapplication.backend.dao;

import com.google.gson.Gson;
import com.upc.myapplication.BuildConfig;
import com.upc.myapplication.backend.dao.datasource.AirtableClient;
import com.upc.myapplication.backend.model.airtable.AirtableRecord;
import com.upc.myapplication.backend.model.airtable.BookFields;
import com.upc.myapplication.backend.model.airtable.AirtableResponse;
import java.io.IOException;
import java.net.URLEncoder;
import com.google.gson.reflect.TypeToken;

public class BookDao {
    private static String entityId = BuildConfig.AIRTABLE_BOOK_TABLE;

    public static AirtableRecord<BookFields>[] getAvailableBooks(){
        try {
            String filter = "AND({Available} > 0)";
            String encodedFilter = URLEncoder.encode(filter, "UTF-8");

            AirtableClient airtableClient = new AirtableClient(entityId);
            String results = airtableClient.searchBy(encodedFilter, null, null);

            TypeToken<AirtableResponse<AirtableRecord<BookFields>>> typeToken = new TypeToken<>(){};
            AirtableResponse<AirtableRecord<BookFields>> response = new Gson().fromJson(results, typeToken.getType());
            return response.getRecords();
        } catch (IOException e) {
            //TODO implement better exception handling
            throw new RuntimeException(e);
        }
    }
}
