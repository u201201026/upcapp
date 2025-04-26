package com.upc.myapplication.backend.model.airtable;

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
}
