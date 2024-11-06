package org.example.parser.utils;


import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Интерфейс загрузки XML документа
 */
public interface XmlDocumentLoader {

    /**
     * Метод получения объекта класса Document (DOM) из XML массива байт
     *
     * @param xmlBytes массив байт XML
     * @return document XML (DOM)
     */
    Document loadDocument(byte[] xmlBytes) throws ParserConfigurationException, SAXException, IOException;
}
