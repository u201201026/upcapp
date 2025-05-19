package com.upc.myapplication.backend.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.upc.myapplication.backend.dao.datasource.ApiClient;
import com.upc.myapplication.backend.model.AuthRequest;
import com.upc.myapplication.backend.model.CreateUserRequest;
import com.upc.myapplication.backend.model.User;
import org.apache.hc.client5.http.HttpResponseException;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.lang.reflect.Type;

public class UserApi {
    public static User authenticate(AuthRequest request){
        try {
            String url = "https://mfcust9ld7.execute-api.us-east-1.amazonaws.com/v1/users/authenticate";
            String result = ApiClient.post(url, request.toString());

            TypeToken<User> typeToken = new TypeToken<>(){};
            return new Gson().fromJson(result, typeToken.getType());
        } catch (HttpResponseException e) {
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User[] getBookLinkedUsers(){
        try {
            String url = "https://mfcust9ld7.execute-api.us-east-1.amazonaws.com/v1/users/book-linked";
            String result = ApiClient.get(url);

            Type userArrayType = new TypeToken<User[]>(){}.getType();
            return new Gson().fromJson(result, userArrayType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User registerUser(CreateUserRequest request) {
        try {
            String url = "https://mfcust9ld7.execute-api.us-east-1.amazonaws.com/v1/users";
            String result = ApiClient.post(url, request.toString());

            TypeToken<User> typeToken = new TypeToken<>() {};
            return new Gson().fromJson(result, typeToken.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getByRecordId(@NotNull String recordId) {
        try {
            String url = "https://mfcust9ld7.execute-api.us-east-1.amazonaws.com/v1/users/" + recordId;
            String result = ApiClient.get(url);

            TypeToken<User> typeToken = new TypeToken<>(){};
            return new Gson().fromJson(result, typeToken.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
