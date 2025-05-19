package com.upc.myapplication.backend.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.upc.myapplication.backend.dao.datasource.ApiClient;
import com.upc.myapplication.backend.model.ReserveBookRequest;
import com.upc.myapplication.backend.model.UserBook;
import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.GsonBuilder;

public class UserBookApi {
    public static void registerUserBook(ReserveBookRequest request){
        try {
            String url = "https://mfcust9ld7.execute-api.us-east-1.amazonaws.com/v1/user-books/reserve-book";
            ApiClient.post(url, request.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void lendBook(String userBookId){
        try{
            String url = "https://mfcust9ld7.execute-api.us-east-1.amazonaws.com/v1/user-books/" + userBookId + "/lend-book";
            ApiClient.post(url, "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void extendLoan(String userBookId){
        try{
            String url = "https://mfcust9ld7.execute-api.us-east-1.amazonaws.com/v1/user-books/" + userBookId + "/extend-loan";
            ApiClient.post(url, "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void returnBook(String userBookId){
        try{
            String url = "https://mfcust9ld7.execute-api.us-east-1.amazonaws.com/v1/user-books/" + userBookId + "/return-book";
            ApiClient.post(url, "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static UserBook[] getAllUserBooks(){
        try {
            String url = "https://mfcust9ld7.execute-api.us-east-1.amazonaws.com/v1/user-books";
            String result = ApiClient.get(url);

            Gson gson = new GsonBuilder()
                    .setDateFormat("MMM dd, yyyy, h:mm:ss a")
                    .create();

            Type userBookArrayType = new TypeToken<UserBook[]>(){}.getType();
            return gson.fromJson(result, userBookArrayType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static UserBook[] getUserBooksByUserRecordId(String userRecordId){
        try {
            String url = "https://mfcust9ld7.execute-api.us-east-1.amazonaws.com/v1/user-books/users/" + userRecordId;
            String result = ApiClient.get(url);

            Gson gson = new GsonBuilder()
                    .setDateFormat("MMM dd, yyyy, h:mm:ss a")
                    .create();

            Type userBookArrayType = new TypeToken<UserBook[]>(){}.getType();
            return gson.fromJson(result, userBookArrayType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
