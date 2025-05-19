package com.upc.myapplication.backend.model;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class CreateUserRequest {
    @SerializedName("nationalId")
    private String nationalId;
    @SerializedName("email")
    private String email;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("password")
    private String password;
    @SerializedName("type")
    private String type;

    public String getNationalId() {
        return nationalId;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NonNull
    public String toString(){
        return new Gson().toJson(this);
    }
}
