package com.linkresearchtools.pandi.service;

import com.linkresearchtools.pandi.model.UrlDoc;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public interface IDataExtractor {

    String getTitle(Element doc);
    String getAuthor(Element doc);
    String getPublishDate(Element doc);
    String getContent(Element doc);

}
