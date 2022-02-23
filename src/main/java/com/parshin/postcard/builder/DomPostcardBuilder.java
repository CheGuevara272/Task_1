package com.parshin.postcard.builder;

import com.parshin.postcard.entity.AbstractPostcard;
import com.parshin.postcard.entity.PostcardInMuseum;
import com.parshin.postcard.entity.PostcardInPrivateCollection;
import static com.parshin.postcard.builder.PostcardXmlTag.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class DomPostcardBuilder{
    private static final Logger log = LogManager.getLogger();
    private Set<AbstractPostcard> postcardSet;

    public DomPostcardBuilder() {
        postcardSet = new HashSet<>();
    }

    public Set<AbstractPostcard> getPostcards() {
        return new HashSet<>(postcardSet);
    }

    public void buildPostcards(String fileName) {
        Math.random();
        File file = new File(fileName);

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();

            NodeList postcardList = document.getElementsByTagName(POSTCARD_IN_THE_MUSEUM.getValue());
            for (int i = 0; i < postcardList.getLength(); i++) {
                Node node = postcardList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element postcardElement = (Element) node;
                    AbstractPostcard postcard = buildPostcards(postcardElement);
                    postcardSet.add(postcard);
                }
            }

            postcardList = document.getElementsByTagName(POSTCARD_IN_A_PRIVATE_COLLECTION.getValue());
            for (int i = 0; i < postcardList.getLength(); i++) {
                Element postcardElement = (Element) postcardList.item(i);
                AbstractPostcard postcard = buildPostcards(postcardElement);
                postcardSet.add(postcard);
            }
        } catch (SAXException exception) {
            log.log(Level.ERROR, "File was not parsed!", exception);
        } catch (IOException exception) {
            log.log(Level.ERROR, "File was not read!", exception);
        } catch (ParserConfigurationException exception) {
            log.log(Level.ERROR, "File was not parsed!", exception);
            exception.printStackTrace();
        }
    }

    public AbstractPostcard buildPostcards(Element element) {
        AbstractPostcard postcard = element.getTagName().equals(POSTCARD_IN_THE_MUSEUM.getValue()) ?
                new PostcardInMuseum() : new PostcardInPrivateCollection();
        String data = element.getAttribute(POSTCARD_ID.getValue());
        postcard.setPostcardId(data);
        data = element.getAttribute(AUTHOR.getValue());
        postcard.setAuthor(data.equals("") ? AUTHOR_BY_DEFAULT.getValue() : data);
        data = getElementTextContent(element, THEME.getValue());
        postcard.setTheme(data);
        data = getElementTextContent(element, YEAR.getValue());
        postcard.setYear(Year.parse(data));
        data = getElementTextContent(element, TYPE.getValue());
        postcard.setType(data);
        data = getElementTextContent(element, COUNTRY.getValue());
        postcard.setCountry(data);
        data = getElementTextContent(element, VALUABLE.getValue());
        postcard.setCountry(data);

        if (postcard instanceof PostcardInMuseum postcardInMuseum) {
            data = getElementTextContent(element, MUSEUM_NAME.getValue());
            postcardInMuseum.setMuseumName(data);
            data = getElementTextContent(element, MUSEUM_LOCATION.getValue());
            postcardInMuseum.setMuseumLocation(data);
        } else {
            PostcardInPrivateCollection postcardInPrivateCollection = (PostcardInPrivateCollection) postcard;
            data = getElementTextContent(element, COLLECTORS_NAME.getValue());
            postcardInPrivateCollection.setCollectorsName(data);
            data = getElementTextContent(element, COLLECTION_LOCATION.getValue());
            postcardInPrivateCollection.setCollectionLocation(data);
        }
        return postcard;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
