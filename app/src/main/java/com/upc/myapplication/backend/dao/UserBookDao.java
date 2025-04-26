package com.upc.myapplication.backend.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.upc.myapplication.BuildConfig;
import com.upc.myapplication.backend.dao.datasource.AirtableClient;
import com.upc.myapplication.backend.model.airtable.AirtableRecord;
import com.upc.myapplication.backend.model.airtable.UserBookFields;
import java.io.IOException;

public class UserBookDao {
    private static String entityId = BuildConfig.AIRTABLE_USERBOOK_TABLE;

    public static AirtableRecord<UserBookFields> registerUserBook(UserBookFields userBookFields){
        try {
            AirtableClient airtableClient = new AirtableClient(entityId);
            TypeToken<AirtableRecord<UserBookFields>> typeToken = new  TypeToken<>() {};
            return new Gson().fromJson(airtableClient.post(userBookFields.toString()), typeToken.getType());
        } catch (IOException e) {
            //TODO implement better exception handling
            throw new RuntimeException(e);
        }
    }
}
