package com.linkresearchtools.pandi.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class SpiegelDataExtractor implements IDataExtractor {

    @Override
    public String getTitle(Element doc) {
//        Element bodyElement = doc.selectFirst("body");
//        return bodyElement.selectFirst("span.headline").text();

        String jsonString = doc .selectFirst("script[type=application/ld+json]")
                .childNode(0).toString();
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(jsonString).getAsJsonObject();
        return o.get("headline").getAsString();
    }

    @Override
    public String getAuthor(Element doc) {
        String jsonString = doc .selectFirst("script[type=application/ld+json]")
                                .childNode(0).toString();
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(jsonString).getAsJsonObject();
        return o.get("author").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();

    }

    @Override
    public String getPublishDate(Element doc) {
//        Element bodyElement = doc.selectFirst("body");
        return doc.selectFirst("time").text();
    }

    @Override
    public String getContent(Element doc) {

//        Element bodyElement = doc.selectFirst("body");
        Element original = doc.selectFirst("div.article-section");
        String content = "";
        for(Node node : original.childNodes()) {
            if(node instanceof TextNode && !((TextNode) node).text().isEmpty())
                content += ((TextNode) node).text();
            if(node instanceof Element)
                content += ((Element)node).select("p").text();
        }

        System.out.println("intro:");
        System.out.println(getArticleIntro(doc));
        System.out.println("creator:");
        System.out.println(getCreator(doc));

        return content.trim();

    }

    public String getArticleIntro(Element doc){
        Element bodyElement = doc.selectFirst("body");
        return bodyElement.selectFirst("p.article-intro").text();
    }

    public String getCreator(Element doc){
        String jsonString = doc .selectFirst("script[type=application/ld+json]")
                .childNode(0).toString();
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(jsonString).getAsJsonObject();
        return o.get("creator").toString();
    }

}
