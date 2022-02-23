package com.parshin.postcard.entity;

import com.parshin.postcard.builder.SaxPostcardBuilder;
import com.parshin.postcard.builder.StaxPostcardBuilder;
import com.parshin.postcard.exception.CustomException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class StaxBuilderTest {
    public StaxPostcardBuilder staxPostcardBuilder;
    public String fileName = "data/postcards.xml";

    @BeforeMethod
    public void setUp() {
        staxPostcardBuilder = new StaxPostcardBuilder();
    }

    @Test
    void buildPostcards() throws CustomException {
        staxPostcardBuilder.buildPostcards(fileName);
        int actual = staxPostcardBuilder.getPostcards().size();
        int expected = 18;
        assertEquals(actual, expected);
    }
}
