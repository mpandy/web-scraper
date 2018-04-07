package com.linkresearchtools.pandi.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class SpiegelDoc extends AbstractDoc {

    private String articleIntro;
    private String creator;
    private List<Comment> lastFiveComments;
    private String commentsCount;

    public SpiegelDoc() {
        super("spiegel.de");
    }

    @Override
    public void extractData(String url) {
        try {
            Element page = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
            super.setBasicInfo(getTitle(page), getAuthor(page), getPublishDate(page), getContent(page));
            this.articleIntro = getArticleIntro(page);
            this.creator = getCreator(page);
            this.lastFiveComments = getComments(page);
            this.commentsCount = getCommentCount(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCommentCount(Element page) {
        return page.selectFirst("div.article-comments-box")
                .select("span")
                .stream()
                .filter(span -> span.text().trim().startsWith("insgesamt"))
                .findFirst()
                .get().text().split(" ")[1];
    }

    private List<Comment> getComments(Element page) {
        return page.select("div.article-comment")
                .stream()
                .map(element -> new Comment(element.select("b").text(),
                                            element.select("div.js-article-post-full-text").text()))
                .collect(Collectors.toList());
    }

    private String getAuthor(Element doc) {
        String jsonString = doc .selectFirst("script[type=application/ld+json]")
                .childNode(0).toString();
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(jsonString).getAsJsonObject();
        return o.get("author").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();

    }

    private String getPublishDate(Element doc) {
        return doc.selectFirst("time").text();
    }

    private String getContent(Element doc) {
        Element original = doc.selectFirst("div.article-section");
        String content = "";
        for(Node node : original.childNodes()) {
            if(node instanceof TextNode && !((TextNode) node).text().isEmpty())
                content += ((TextNode) node).text();
            if(node instanceof Element)
                content += ((Element)node).select("p").text();
        }
        return content.trim();
    }

    private String getTitle(Element page) {
        String jsonString = page.selectFirst("script[type=application/ld+json]")
                .childNode(0).toString();
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(jsonString).getAsJsonObject();
        return o.get("headline").getAsString();
    }

    private String getCreator(Element doc){
        String jsonString = doc .selectFirst("script[type=application/ld+json]")
                .childNode(0).toString();
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(jsonString).getAsJsonObject();
        return o.get("creator").toString().replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"","");
    }

    private String getArticleIntro(Element page) {
        return page.selectFirst("p.article-intro").text();
    }

}
