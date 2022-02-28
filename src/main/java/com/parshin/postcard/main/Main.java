package com.parshin.postcard.main;

import com.parshin.postcard.builder.DomPostcardBuilder;
import com.parshin.postcard.builder.SaxPostcardBuilder;
import com.parshin.postcard.builder.StaxPostcardBuilder;
import com.parshin.postcard.exception.CustomException;

public class Main {
    public static void main(String[] args) {
        String fileName = "data/postcards.xml";
        SaxPostcardBuilder saxPostcardBuilder = new SaxPostcardBuilder();
        saxPostcardBuilder.buildPostcards(fileName);

        DomPostcardBuilder domPostcardBuilder = new DomPostcardBuilder();
        domPostcardBuilder.buildPostcards(fileName);

        StaxPostcardBuilder staxPostcardBuilder = new StaxPostcardBuilder();
        try {
            staxPostcardBuilder.buildPostcards(fileName);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }
}
