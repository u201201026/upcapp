package com.upc.myapplication.backend.model;

import java.util.Date;

public class UserBook {
    private String id;
    private User user;
    private Book book;
    private String status;
    private Date reserveDueDate;
    private Integer lendNumber;
    private Date lendDueDate;
    private Date returnedDate;

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
}
