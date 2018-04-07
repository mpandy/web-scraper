package com.linkresearchtools.pandi.io.process;

import com.linkresearchtools.pandi.context.ExtractorsContext;
import com.linkresearchtools.pandi.model.AbstractDoc;
import java.net.URI;
import java.net.URISyntaxException;

public class BrowserProcessor implements IProcessor {

    final String NO_EXTRACTOR_FOUND = "No data extractor found for this domain!";

    @Override
    public String readUrl(String url) {

            String domainName = getDomainName(url);
            AbstractDoc abstractDoc = ExtractorsContext.getDocProcessor(domainName);

            if(abstractDoc == null)
                return  NO_EXTRACTOR_FOUND+"\n"+ExtractorsContext.existingDataExtractor();

            abstractDoc.extractData(url);
            return abstractDoc.toString();
    }

    private String getDomainName(String url) {
        URI uri = null;
        try {
            uri = new URI(url.trim());
        } catch (URISyntaxException e) {
            return "";
        }
        String domain = uri.getHost();
        if(domain != null)
            return domain.startsWith("www.") ? domain.substring(4) : domain;
        else
            return "";
    }
}
