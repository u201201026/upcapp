package com.upc.myapplication.backend.model;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class AuthRequest {
    @SerializedName("nationalId")
    private String nationalId;
    @SerializedName("password")
    private String password;

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    public String toString(){
        return new Gson().toJson(this);
    }
}
