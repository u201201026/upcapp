package com.upc.myapplication.backend.model.airtable;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class UserFields {
    @SerializedName("ID")
    private String id;
    @SerializedName("Email")
    private String email;
    @SerializedName("FullName")
    private String fullName;
    @SerializedName("Type")
    private String type;
    @SerializedName("Password")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    public String toString(){
        String gson = new Gson().toJson(this);
        gson = "{ \"fields\": " + gson + "}";
        return gson;
    }
}
