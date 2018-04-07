package com.linkresearchtools.pandi.service;

import com.linkresearchtools.pandi.model.UrlDoc;
import org.jsoup.nodes.Element;

public class SeoDataExtractor implements IDataExtractor{

    @Override
    public String getTitle(Element body) {
        return body.getElementsByTag("h1").text();
    }

    @Override
    public String getAuthor(Element body) {
        return body.selectFirst("a[rel=author]").text();
    }

    @Override
    public String getPublishDate(Element body) {
        return body.selectFirst("span.time").text();
    }

    @Override
    public String getContent(Element body) {
        System.out.println(getCommentCount(body));

        return body .selectFirst("div.postarea")
                    .select("p:not(:has(span))").text();
    }

    private String getCommentCount(Element body){
            return body.selectFirst("span.icomment").text().split(" ")[0];
        }

    }
