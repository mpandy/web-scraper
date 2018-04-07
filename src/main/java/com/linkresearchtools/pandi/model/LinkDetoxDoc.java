package com.linkresearchtools.pandi.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.net.URL;

public class LinkDetoxDoc extends AbstractDoc {

    private String lastUpdate;
    private String prevTitle;
    private String nextTitle;

    public LinkDetoxDoc() {
        super("help.linkdetox.com");
    }

    @Override
    public void extractData(String url) {
        try {
            Element page = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
            super.setBasicInfo(getTitle(page), getAuthor(page), getPublishDate(page), getContent(page));
            this.lastUpdate = getLastUpdate(page);
            this.prevTitle = getPrevTitle(page);
            this.nextTitle = getNextTitle(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPrevTitle(Element page) {
        return page.selectFirst("li.previous").select("a").text();
    }

    private String getNextTitle(Element page) {
        return page.selectFirst("li.next").select("a").text();
    }

    private String getTitle(Element page) {
        return page.selectFirst("h1.lesson").text();
    }

    private String getAuthor(Element page) {
        return "";
    }

    private String getPublishDate(Element page) {
        return "";
    }

    private String getContent(Element page) {
        return page.select("div.screensteps-textblock").text();
    }

    private String getLastUpdate(Element page){
        return page.getElementById("lesson-sidebar-info").select("p").text();
    }
}
