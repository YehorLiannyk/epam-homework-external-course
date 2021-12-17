package main.ua.university.finalTask.dal.xsdValidation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class CountriesErrorHandler implements ErrorHandler {
    private static Logger logger = LogManager.getLogger("");
    public void warning(SAXParseException e) {
        logger.warn(getLineColumnNumber(e) + " - " + e.getMessage());
    }
    public void error(SAXParseException e) {
        logger.error(getLineColumnNumber(e) + " - " + e.getMessage());
    }
    public void fatalError(SAXParseException e) {
        logger.fatal(getLineColumnNumber(e) + " - " + e.getMessage());
    }
    private String getLineColumnNumber(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}