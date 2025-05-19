package com.upc.myapplication.backend.model;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class UserBook {
    @SerializedName("id")
    private String id;
    @SerializedName("userRecordId")
    private String userRecordId;
    @SerializedName("bookRecordId")
    private String bookRecordId;
    @SerializedName("status")
    private String status;
    @SerializedName("reserveDueDate")
    private Date reserveDueDate;
    @SerializedName("lendNumber")
    private Integer lendNumber;
    @SerializedName("lendDueDate")
    private Date lendDueDate;
    @SerializedName("returnedDate")
    private Date returnedDate;
    private User user;
    private Book book;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReserveDueDate() {
        return reserveDueDate;
    }

    public void setReserveDueDate(Date reserveDueDate) {
        this.reserveDueDate = reserveDueDate;
    }

    public Integer getLendNumber() {
        return lendNumber;
    }

    public void setLendNumber(Integer lendNumber) {
        this.lendNumber = lendNumber;
    }

    public Date getLendDueDate() {
        return lendDueDate;
    }

    public void setLendDueDate(Date lendDueDate) {
        this.lendDueDate = lendDueDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

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
