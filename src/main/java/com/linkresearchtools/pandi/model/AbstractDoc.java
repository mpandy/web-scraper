package com.linkresearchtools.pandi.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractDoc {

    private String domain;
    private String title;
    private String author;
    private String publishDate;
    private String content;

    public AbstractDoc(String domain) {
        this.domain = domain;
    }

    public abstract void extractData(String url);

    public void setBasicInfo(String title, String author, String publishDate, String content){
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.content = content;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
