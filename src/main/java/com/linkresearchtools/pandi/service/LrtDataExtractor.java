package com.linkresearchtools.pandi.service;

import com.linkresearchtools.pandi.model.UrlDoc;
import org.jsoup.nodes.Element;

public class LrtDataExtractor implements IDataExtractor {

    @Override
    public String getTitle(Element body) {
        return body.selectFirst("h1.lesson").text();
    }

    @Override
    public String getAuthor(Element body) {
        return "";
    }

    @Override
    public String getPublishDate(Element body) {
        return "";
    }

    @Override
    public String getContent(Element body) {
        System.out.println(getLastUpdate(body));
        return body.select("div.screensteps-textblock").text();
    }

    private String getLastUpdate(Element body){
     return body.getElementById("lesson-sidebar-info").select("p").text();
    }
}
