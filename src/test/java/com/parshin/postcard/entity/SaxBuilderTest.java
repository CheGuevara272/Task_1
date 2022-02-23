package com.parshin.postcard.entity;

import com.parshin.postcard.builder.DomPostcardBuilder;
import com.parshin.postcard.builder.SaxPostcardBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SaxBuilderTest {
    public SaxPostcardBuilder saxPostcardBuilder;
    public String fileName = "data/postcards.xml";

    @BeforeMethod
    public void setUp() {
        saxPostcardBuilder = new SaxPostcardBuilder();
    }

    @Test
    void buildPostcards() {
        saxPostcardBuilder.buildPostcards(fileName);
        int actual = saxPostcardBuilder.getPostcards().size();
        int expected = 18;
        assertEquals(actual, expected);
    }
}
