package com.upc.myapplication.backend.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.upc.myapplication.BuildConfig;
import com.upc.myapplication.backend.dao.datasource.AirtableClient;
import com.upc.myapplication.backend.model.airtable.AirtableRecord;
import com.upc.myapplication.backend.model.airtable.AirtableResponse;
import com.upc.myapplication.backend.model.airtable.UserFields;
import java.io.IOException;
import java.net.URLEncoder;

public class UserDao {
    private static String entityId = BuildConfig.AIRTABLE_USER_TABLE;

    public static AirtableRecord<UserFields>[] getUserByIdAndPassword(String id, String password){
        try {
            String filter = "AND({ID} = '" + id + "', {Password} = '" + password + "')";
            String encodedFilter = URLEncoder.encode(filter, "UTF-8");

            AirtableClient airtableClient = new AirtableClient(entityId);
            String results = airtableClient.searchBy(encodedFilter, null, null);

            TypeToken<AirtableResponse<AirtableRecord<UserFields>>> typeToken = new TypeToken<>(){};
            AirtableResponse<AirtableRecord<UserFields>> response = new Gson().fromJson(results, typeToken.getType());
            return response.getRecords();
        } catch (IOException e) {
            //TODO implement better exception handling
            throw new RuntimeException(e);
        }
    }

    public static AirtableRecord<UserFields> create(UserFields userFields) {
        try {
            AirtableClient airtableClient = new AirtableClient(entityId);
            TypeToken<AirtableRecord<UserFields>> typeToken = new  TypeToken<>() {};
            return new Gson().fromJson(airtableClient.post(userFields.toString()), typeToken.getType());
        } catch (IOException e) {
            //TODO implement better exception handling
            throw new RuntimeException(e);
        }
    }

}
