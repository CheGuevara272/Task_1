package com.parshin.postcard.builder;

import com.parshin.postcard.entity.AbstractPostcard;
import com.parshin.postcard.entity.PostcardInMuseum;
import com.parshin.postcard.entity.PostcardInPrivateCollection;
import com.parshin.postcard.exception.CustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.parshin.postcard.builder.PostcardXmlTag.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.time.Year;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class StaxPostcardBuilder{
    private static final Logger log = LogManager.getLogger();
    private Set<AbstractPostcard> postcardSet;

    public StaxPostcardBuilder() {
        postcardSet = new HashSet<>();
    }

    public Set<AbstractPostcard> getPostcards() {
        return new HashSet<>(postcardSet);
    }

    public void buildPostcards(String fileName) throws CustomException {
        File file = new File(fileName);
        String name;
        try (FileInputStream input = new FileInputStream(file)) {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(POSTCARD_IN_THE_MUSEUM.getValue()) || name.equals(POSTCARD_IN_A_PRIVATE_COLLECTION.getValue())) {
                        AbstractPostcard postcard = buildPostcard(reader);
                        postcardSet.add(postcard);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            log.log(Level.ERROR, "File {} had not been found", file, e);
        } catch (IOException e) {
            log.log(Level.ERROR, "File {} can not be read", file, e);
        } catch (XMLStreamException e) {
            log.log(Level.ERROR, "File {} can not be parsed", file, e);
        }
    }

    private AbstractPostcard buildPostcard(XMLStreamReader reader) throws XMLStreamException, CustomException {
        AbstractPostcard postcard = reader.getLocalName().equals(POSTCARD_IN_THE_MUSEUM.getValue()) ?
                new PostcardInMuseum() : new PostcardInPrivateCollection();
        postcard.setPostcardId(reader.getAttributeValue(null, POSTCARD_ID.getValue()));
        String optionalAuthor;
        optionalAuthor = (reader.getAttributeValue(null, AUTHOR.getValue()));
        postcard.setAuthor(optionalAuthor == null ? AUTHOR_BY_DEFAULT.getValue() : optionalAuthor);
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    getStartElement(reader, postcard);
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    String name = reader.getLocalName();
                    if (name.equals(POSTCARD_IN_THE_MUSEUM.getValue()) || name.equals(POSTCARD_IN_A_PRIVATE_COLLECTION.getValue())) {
                        return postcard;
                    }
                }
            }
        }
        log.log(Level.ERROR, "Parsing error. Element is unknown");
        throw new CustomException("Parsing error. Element is unknown");
    }

    private void getStartElement(XMLStreamReader reader, AbstractPostcard postcard) throws XMLStreamException, CustomException {
        String name = reader.getLocalName().toUpperCase(Locale.ROOT);
        String data = getTextFromReader(reader);
        switch (PostcardXmlTag.valueOf(name)) {
            case THEME -> postcard.setTheme(data);
            case YEAR -> postcard.setYear(Year.parse(data));
            case TYPE -> postcard.setType(data);
            case COUNTRY -> postcard.setCountry(data);
            case VALUABLE -> postcard.setValuable(data);
            case MUSEUM_NAME -> {
                PostcardInMuseum postcardInMuseum = (PostcardInMuseum) postcard;
                postcardInMuseum.setMuseumName(data);
            }
            case MUSEUM_LOCATION -> {
                PostcardInMuseum postcardInMuseum = (PostcardInMuseum) postcard;
                postcardInMuseum.setMuseumLocation(data);
            }
            case COLLECTORS_NAME -> {
                PostcardInPrivateCollection postcardInCollection = (PostcardInPrivateCollection) postcard;
                postcardInCollection.setCollectorsName(data);
            }
            case COLLECTION_LOCATION -> {
                PostcardInPrivateCollection postcardInCollection = (PostcardInPrivateCollection) postcard;
                postcardInCollection.setCollectionLocation(data);
            }
            default -> {
                log.log(Level.ERROR, "Unknown tag: {}", name);
                throw new CustomException("Unknown tag: " + name);
            }
        }
    }

    private String getTextFromReader(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
