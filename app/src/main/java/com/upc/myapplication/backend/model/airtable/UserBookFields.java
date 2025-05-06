package com.upc.myapplication.backend.model.airtable;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class UserBookFields {
    @SerializedName("User")
    private String[] userRecordId;
    @SerializedName("Book")
    private String[] bookRecordId;
    @SerializedName("Status")
    private String status;
    @SerializedName("ReserveDueDate")
    private String reserveDueDate;
    @SerializedName("LendDate")
    private String lendDate;
    @SerializedName("LendNumber")
    private int lendNumber;
    @SerializedName("LendDueDate")
    private String lendDueDate;
    @SerializedName("ReturnedDate")
    private String returnedDate;

    public String[] getUserRecordId() {
        return userRecordId;
    }

    public void setUserRecordId(String[] userRecordId) {
        this.userRecordId = userRecordId;
    }

    public String[] getBookRecordId() {
        return bookRecordId;
    }

    public void setBookRecordId(String[] bookRecordId) {
        this.bookRecordId = bookRecordId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReserveDueDate() {
        return reserveDueDate;
    }

    public void setReserveDueDate(String reserveDueDate) {
        this.reserveDueDate = reserveDueDate;
    }

    public int getLendNumber() {
        return lendNumber;
    }

    public void setLendNumber(int lendNumber) {
        this.lendNumber = lendNumber;
    }

    public String getLendDueDate() {
        return lendDueDate;
    }

    public void setLendDueDate(String lendDueDate) {
        this.lendDueDate = lendDueDate;
    }

    public String getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(String returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String getLendDate() {
        return lendDate;
    }

    public void setLendDate(String lendDate) {
        this.lendDate = lendDate;
    }

    @NonNull
    public String toString(){
        String gson = new Gson().toJson(this);
        gson = "{ \"fields\": " + gson + "}";
        return gson;
    }
}
