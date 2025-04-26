package com.upc.myapplication.backend.model.airtable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class BookFields {
    @SerializedName("Title")
    private String title;
    @SerializedName("Author")
    private String author;
    @SerializedName("Genre")
    private String genre;
    @SerializedName("Year")
    private String year;
    @SerializedName("Attachment")
    private Attachment[] attachment;
    @SerializedName("Stock")
    private String stock;
    @SerializedName("Unavailable")
    private String unavailable;
    @SerializedName("Available")
    private String available;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Attachment[] getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment[] attachment) {
        this.attachment = attachment;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getUnavailable() {
        return unavailable;
    }

    public void setUnavailable(String unavailable) {
        this.unavailable = unavailable;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String toString(){
        String gson = new Gson().toJson(this);
        gson = "{ \"fields\": " + gson + "}";
        return gson;
    }
}
