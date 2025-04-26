package com.upc.myapplication.backend.model.airtable;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class UserBookFields {
    @SerializedName("User")
    private String[] user;
    @SerializedName("Book")
    private String[] book;
    @SerializedName("Status")
    private String status;

    public String[] getUser() {
        return user;
    }

    public void setUser(String[] user) {
        this.user = user;
    }

    public String[] getBook() {
        return book;
    }

    public void setBook(String[] book) {
        this.book = book;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NonNull
    public String toString(){
        String gson = new Gson().toJson(this);
        gson = "{ \"fields\": " + gson + "}";
        return gson;
    }
}
