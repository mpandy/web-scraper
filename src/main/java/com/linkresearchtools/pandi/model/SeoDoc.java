package com.linkresearchtools.pandi.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeoDoc extends AbstractDoc {

    private String commentsCount;
    private List<Comment> comments;

    public SeoDoc() {
        super("seo.de");
    }

    @Override
    public void extractData(String url) {
        try {
            Element page = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
            super.setBasicInfo(getTitle(page), getAuthor(page), getPublishDate(page), getContent(page));
            this.commentsCount = getCommentCount(page);
            this.comments = getComments(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Comment> getComments(Element page) {
        Elements commentElements = page.select("li.comment");
        return commentElements
                .stream()
                .map(element -> new Comment(element.select("cite.fn").text(),
                                            element.select("p").text()))
                .collect(Collectors.toList());
    }

    private String getTitle(Element page) {
        return page.getElementsByTag("h1").text();
    }

    private String getAuthor(Element page) {
        return page.selectFirst("a[rel=author]").text();
    }

    private String getPublishDate(Element page) {
        return page.selectFirst("span.time").text();
    }

    private String getContent(Element page) {
        return page .selectFirst("div.postarea")
                .select("p:not(:has(span))").text();
    }

    private String getCommentCount(Element page){
        return page.selectFirst("span.icomment").text().split(" ")[0];
    }
}
