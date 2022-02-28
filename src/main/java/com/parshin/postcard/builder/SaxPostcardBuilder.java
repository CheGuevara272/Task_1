package com.parshin.postcard.builder;

import com.parshin.postcard.entity.AbstractPostcard;
import com.parshin.postcard.handler.PostcardErrorHandler;
import com.parshin.postcard.handler.PostcardHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SaxPostcardBuilder{
    private static final Logger log = LogManager.getLogger();
    private Set<AbstractPostcard> postcardSet;
    private XMLReader reader;
    private SAXParserFactory factory = SAXParserFactory.newInstance();
    private SAXParser saxParser;

    public SaxPostcardBuilder() {
        postcardSet = new HashSet<>();
    }

    public Set<AbstractPostcard> getPostcards() {
        return new HashSet<>(postcardSet);
    }

    public void buildPostcards(String fileName) {
        File file = new File(fileName);
        try {
            saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
            PostcardHandler handler = new PostcardHandler();
            reader.setContentHandler(handler);
            reader.setErrorHandler(new PostcardErrorHandler());
            reader.parse(String.valueOf(file));
            postcardSet = handler.getPostcards();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
