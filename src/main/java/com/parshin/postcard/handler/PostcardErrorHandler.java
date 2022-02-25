package com.parshin.postcard.handler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class PostcardErrorHandler implements ErrorHandler {
    private static final Logger log = LogManager.getLogger();

    public void warning(SAXParseException e) {
        log.log(Level.WARN,"{} - {}", getLineColumn(e), e.getMessage());
    }

    @Override
    public void error(SAXParseException e) {
        log.log(Level.ERROR,"{} - {}", getLineColumn(e), e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        log.log(Level.FATAL,"{} - {}", getLineColumn(e), e.getMessage());
    }

    private String getLineColumn(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
