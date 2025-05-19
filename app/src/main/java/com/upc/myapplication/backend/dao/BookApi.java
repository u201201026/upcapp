package com.upc.myapplication.backend.dao;

import com.google.gson.Gson;
import com.upc.myapplication.backend.dao.datasource.ApiClient;
import com.upc.myapplication.backend.model.Book;
import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;

public class BookApi {
    public static Book[] getAllBooks(){
        try {
            String url = "https://mfcust9ld7.execute-api.us-east-1.amazonaws.com/v1/books";
            String results = ApiClient.get(url);

            Type bookArrayType = new TypeToken<Book[]>(){}.getType();
            return new Gson().fromJson(results, bookArrayType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Book[] getAvailableBooks(){
        try {
            String url = "https://mfcust9ld7.execute-api.us-east-1.amazonaws.com/v1/books/available";
            String results = ApiClient.get(url);

            Type bookArrayType = new TypeToken<Book[]>(){}.getType();
            return new Gson().fromJson(results, bookArrayType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Book getByRecordId(@NotNull String recordId) {
        try {
            String url = "https://mfcust9ld7.execute-api.us-east-1.amazonaws.com/v1/books/" + recordId;
            String results = ApiClient.get(url);

            TypeToken<Book> typeToken = new TypeToken<>(){};
            return new Gson().fromJson(results, typeToken.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Book[] getUserLinkedBooks(){
        try {
            String url = "https://mfcust9ld7.execute-api.us-east-1.amazonaws.com/v1/books/user-linked";
            String results = ApiClient.get(url);

            Type bookArrayType = new TypeToken<Book[]>(){}.getType();
            return new Gson().fromJson(results, bookArrayType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
