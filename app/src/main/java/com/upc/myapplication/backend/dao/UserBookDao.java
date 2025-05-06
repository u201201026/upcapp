package com.upc.myapplication.backend.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.upc.myapplication.BuildConfig;
import com.upc.myapplication.backend.dao.datasource.AirtableClient;
import com.upc.myapplication.backend.model.airtable.AirtableRecord;
import com.upc.myapplication.backend.model.airtable.AirtableResponse;
import com.upc.myapplication.backend.model.airtable.UserBookFields;
import java.io.IOException;
import java.net.URLEncoder;

public class UserBookDao {
    private static String entityId = BuildConfig.AIRTABLE_USERBOOK_TABLE;

    public static AirtableRecord<UserBookFields> registerUserBook(UserBookFields userBookFields){
        try {
            AirtableClient airtableClient = new AirtableClient(entityId);
            TypeToken<AirtableRecord<UserBookFields>> typeToken = new  TypeToken<>() {};
            return new Gson().fromJson(airtableClient.post(userBookFields.toString()), typeToken.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateUserBook(AirtableRecord<UserBookFields> userBookRecord){
        try{
            AirtableClient airtableClient = new AirtableClient(entityId);
            airtableClient.put(userBookRecord.getId(), userBookRecord.getFields().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AirtableRecord<UserBookFields>[] getAllUserBooks(){
        return searchUserBooks("");
    }

    public static AirtableRecord<UserBookFields>[] getUserBooks(String userRecordId){
        return searchUserBooks("AND({User} = '" + userRecordId + "')");
    }

    private static AirtableRecord<UserBookFields>[] searchUserBooks(String filter){
        try {
            String encodedFilter = URLEncoder.encode(filter, "UTF-8");

            AirtableClient airtableClient = new AirtableClient(entityId);
            String results = airtableClient.searchBy(encodedFilter, null, null);

            TypeToken<AirtableResponse<AirtableRecord<UserBookFields>>> typeToken = new TypeToken<>(){};
            AirtableResponse<AirtableRecord<UserBookFields>> response = new Gson().fromJson(results, typeToken.getType());
            return response.getRecords();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
