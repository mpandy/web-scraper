package com.linkresearchtools.pandi.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UrlDoc {
    private String title;
    private String content;
    private String author;
    private String publishDate;

    public UrlDoc(String title, String content, String author, String publishDate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);    }

}
