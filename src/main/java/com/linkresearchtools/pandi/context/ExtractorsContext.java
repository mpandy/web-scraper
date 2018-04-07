package com.linkresearchtools.pandi.context;

import com.linkresearchtools.pandi.model.AbstractDoc;
import com.linkresearchtools.pandi.model.LinkDetoxDoc;
import com.linkresearchtools.pandi.model.SeoDoc;
import com.linkresearchtools.pandi.model.SpiegelDoc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ExtractorsContext {

    public static List<AbstractDoc> availableDataExtractors;
    static {
        availableDataExtractors = new ArrayList<>();
        availableDataExtractors.add(new SpiegelDoc());
        availableDataExtractors.add(new LinkDetoxDoc());
        availableDataExtractors.add(new SeoDoc());
    }

    public static AbstractDoc getDocProcessor(String domain){
        try {
            return availableDataExtractors
                    .stream()
                    .filter(doc -> doc.getDomain().equals(domain))
                    .findAny()
                    .get();
        }
        catch (NoSuchElementException noSuchElementException){
            return null;
        }
    }

    public static String existingDataExtractor(){
        return "Available domains: "+
                availableDataExtractors
                        .stream()
                        .map(doc -> doc.getDomain())
                        .collect(Collectors.toList())
                        .toString();
    }

}
