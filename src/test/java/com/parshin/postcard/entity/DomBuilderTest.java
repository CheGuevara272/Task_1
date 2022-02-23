package com.parshin.postcard.entity;

import com.parshin.postcard.builder.DomPostcardBuilder;
import com.parshin.postcard.builder.SaxPostcardBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DomBuilderTest {
    public DomPostcardBuilder domPostcardBuilder;
    public String fileName = "data/postcards.xml";

    @BeforeMethod
    public void setUp() {
        domPostcardBuilder = new DomPostcardBuilder();
    }

    @Test
    void buildPostcards() {
        domPostcardBuilder.buildPostcards(fileName);
        int actual = domPostcardBuilder.getPostcards().size();
        int expected = 18;
        assertEquals(actual, expected);
    }
}