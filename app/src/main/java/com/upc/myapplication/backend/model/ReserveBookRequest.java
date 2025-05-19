package com.upc.myapplication.backend.model;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class ReserveBookRequest {
    @SerializedName("userRecordId")
    private String userRecordId;
    @SerializedName("bookRecordId")
    private String bookRecordId;

    public String getUserRecordId() {
        return userRecordId;
    }

    public void setUserRecordId(String userRecordId) {
        this.userRecordId = userRecordId;
    }

    public String getBookRecordId() {
        return bookRecordId;
    }

    public void setBookRecordId(String bookRecordId) {
        this.bookRecordId = bookRecordId;
    }

    @NonNull
    public String toString(){
        return new Gson().toJson(this);
    }
}
